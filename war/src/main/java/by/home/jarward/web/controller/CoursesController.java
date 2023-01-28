package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.*;
import by.home.jarward.web.service.intarfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping(value = "/courses")
public class CoursesController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    LessonService lessonService;
    @Autowired
    MarkService markService;


    @GetMapping(value = "/all")
    public ModelAndView getAllCourses(@RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("courses");
        Pageable pageWithThreeElements = PageRequest.of(Integer.parseInt(pageNumber), 3);
        Page<Course> page;

        if (auth.getName().equals("anonymousUser") || (auth.getAuthorities().stream().anyMatch(item -> item.getAuthority().equals("ROLE_teacher")))) {
            page = courseService.getAllByDateStartGreaterThan(LocalDate.now(), pageWithThreeElements);
        } else {
            Object principal = auth.getPrincipal();
            String login = ((UserDetails) principal).getUsername();
            page = courseService.getAllByDateStartGreaterThanByLoginNot(LocalDate.now(), login, pageWithThreeElements);
        }

        List<Course> courses = page.get().toList();
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public RedirectView getRegistrationPage(@RequestParam("course") String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String loginPrincipal = ((UserDetails) principal).getUsername();
        Optional<User> student = userService.getByLogin(loginPrincipal);
        Optional<Course> course = courseService.getByIdWithStudents(Long.parseLong(id));
        course.ifPresent(itemCourse -> {
                    student.ifPresent(itemStudent ->
                            itemCourse.getStudents().add((Student) itemStudent));
                    courseService.save(itemCourse);
                }
        );

        return new RedirectView("/courses/all");
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getCoursePage(@PathVariable("id") String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String loginPrincipal = ((UserDetails) principal).getUsername();
        ModelAndView modelAndView = new ModelAndView("course");
        var ref = new Object() {
            List<Homework> homeworkList;
            List<Lesson> lessons;
            List<Mark> marks;
        };

        Optional<Course> course = courseService.getByIdWithStudents(Long.parseLong(id));

        if (auth.getAuthorities().stream()
                .anyMatch(item -> item.getAuthority().equals("ROLE_student"))) {
            Optional<User> student = userService.getByLogin(loginPrincipal);
            ref.homeworkList = homeworkService.getAllByCourseId(Long.parseLong(id));
            course.ifPresent(itemCourse -> ref.lessons = lessonService.getAllByCourse(itemCourse));
            student.ifPresent(itemStudent -> ref.marks = markService.getByHomeworksAndStudent(ref.homeworkList, itemStudent));

            OptionalDouble average = ref.marks.stream()
                    .map(Mark::getMark)
                    .mapToInt(item -> item)
                    .average();

            modelAndView.addObject("marks", ref.marks);
            average.ifPresent(itemAverage -> modelAndView.addObject("average", itemAverage));

        } else if (auth.getAuthorities().stream()
                .anyMatch(item -> item.getAuthority().equals("ROLE_teacher"))) {
            ref.homeworkList = homeworkService.getAllByCourseId(Long.parseLong(id));
            course.ifPresent(itemCourse -> ref.lessons = lessonService.getAllByCourse(itemCourse));
        }

        modelAndView.addObject("lessons", ref.lessons);
        modelAndView.addObject("homeworks", ref.homeworkList);
        course.ifPresent(itemCourse -> modelAndView.addObject("course", itemCourse));

        return modelAndView;
    }

    @GetMapping
    public ModelAndView getCoursesByUser(@RequestParam("login") String login,
                                         @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String loginPrincipal = ((UserDetails) principal).getUsername();
        if (!login.equals(loginPrincipal)) {
            throw new AuthenticationServiceException("Access denied");
        }

        Pageable pageWithThreeElements = PageRequest.of(Integer.parseInt(pageNumber), 3);
        ModelAndView modelAndView = new ModelAndView("courses");
        List<Course> courses = new ArrayList<>();
        Page<Course> page = null;

        if (auth.getAuthorities().stream()
                .anyMatch(item -> item.getAuthority().equals("ROLE_teacher"))) {
            page = courseService.getAllByTeacherLogin(login, pageWithThreeElements);
            courses = page.get().toList();

        } else if (auth.getAuthorities().stream()
                .anyMatch(item -> item.getAuthority().equals("ROLE_student"))) {
            page = courseService.getAllByDateEndGreaterThanAndLogin(LocalDate.now(), loginPrincipal, pageWithThreeElements);
            courses = page.get().toList();
        }

        modelAndView.addObject("isMyCourses", true);
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

}
