package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.entity.Mark;
import by.home.jarward.jar.entity.MarkId;
import by.home.jarward.jar.entity.Student;
import by.home.jarward.web.form.HomeworkForm;
import by.home.jarward.web.service.intarfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView getHomeworksPage(@ModelAttribute("homeworkForm") HomeworkForm homeworkForm, @RequestParam("id") String id) {

        ModelAndView modelAndView = new ModelAndView("homework");
        var ref = new Object() {
            List<Student> students;
            Homework homework;
            List<Mark> marks;
        };

        homeworkService.getById(Long.parseLong(id)).ifPresent(item -> {
            ref.homework = item;
            Long idCourse = item.getCourse().getId();
            courseService.getByIdWithStudents(idCourse).ifPresent(itemCourse ->
                    ref.students = itemCourse.getStudents());
        });

        homeworkForm.setStudentName(ref.marks.get(0).getMarkId().getStudent().getName());
        homeworkForm.setLogin(ref.marks.get(0).getMarkId().getStudent().getLogin());
        modelAndView.addObject("students", ref.students);

        return modelAndView;
    }

}
