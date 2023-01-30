package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Teacher;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getTeachersPage(){

        ModelAndView modelAndView = new ModelAndView("teachers");
        List<Teacher> teachers = userService.getAllTeachers();
        modelAndView.addObject("teachers", teachers);
        System.out.println(teachers.size());
        return modelAndView;
    }

}
