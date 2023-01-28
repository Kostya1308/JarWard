package by.home.jarward.web.controller;

import by.home.jarward.web.service.intarfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/homeworks")
public class HomeworkController {

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

    @GetMapping
    public ModelAndView getHomeworksPage(){

        ModelAndView modelAndView = new ModelAndView("homework");
        return modelAndView;
    }

}
