package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.*;
import by.home.jarward.web.service.intarfaces.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
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
import java.util.stream.Collectors;

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
        ModelAndView modelAndView = new ModelAndView("courses");
        Pageable pageWithThreeElements = PageRequest.of(Integer.parseInt(pageNumber), 3);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Page<Course> page;

        if (auth.getName().equals("anonymousUser")) {
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginPrincipal = ((UserDetails) principal).getUsername();

        Optional<User> student = userService.getByLogin(loginPrincipal);
        Optional<Course> course = courseService.getById(Long.parseLong(id));
        List<Homework> homeworkList = homeworkService.getAllByCourseId(Long.parseLong(id));
        final List<Mark>[] marks = new List[]{new ArrayList<>()};
        student.ifPresent(itemStudent -> marks[0] = markService.getByHomeworksAndStudent(homeworkList, itemStudent));
        OptionalDouble average = marks[0].stream()
                .map(Mark::getMark)
                .mapToInt(item -> item)
                .average();

        ModelAndView modelAndView = new ModelAndView("course");
        modelAndView.addObject("marks", marks[0]);
        modelAndView.addObject("homeworks", homeworkList);
        course.ifPresent(itemCourse -> modelAndView.addObject("course", itemCourse));
        average.ifPresent(itemAverage -> modelAndView.addObject("average", itemAverage));

        return modelAndView;
    }

    @GetMapping(value = "/user/{login}")
    public ModelAndView getCoursesByUser(@PathVariable("login") String login,
                                         @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginPrincipal = ((UserDetails) principal).getUsername();
        if (!login.equals(loginPrincipal)) {
            throw new AuthenticationServiceException("Access denied");
        }

        ModelAndView modelAndView = new ModelAndView("courses");
        Pageable pageWithThreeElements = PageRequest.of(Integer.parseInt(pageNumber), 3);

        Page<Course> page = courseService.getAllByDateEndGreaterThanAndLogin(LocalDate.now(), loginPrincipal, pageWithThreeElements);
        List<Course> courses = page.get().toList();
        modelAndView.addObject("isMyCourses", true);
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("page", page);

        return modelAndView;

    }

}
