package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Teacher;
import by.home.jarward.web.service.intarfaces.CourseService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/courses")
public class CoursesController {
    @Autowired
    CourseService courseService;

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
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration");
    }

    @GetMapping(value = "/{login}")
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
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("page", page);

        return modelAndView;

    }

}
