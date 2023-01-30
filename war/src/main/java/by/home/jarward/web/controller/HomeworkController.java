package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.entity.Mark;
import by.home.jarward.jar.entity.MarkId;
import by.home.jarward.jar.entity.Student;
import by.home.jarward.web.form.MarkForm;
import by.home.jarward.web.form.MarkIdForm;
import by.home.jarward.web.service.intarfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/homeworks")
public class HomeworkController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private MarkService markService;

    @GetMapping(value = "/{id}")
    public ModelAndView getHomeworksPage(@ModelAttribute("markForm") MarkForm markForm, @PathVariable("id") String id) {

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

        ref.marks = markService.getByHomeworkAndStudents(ref.homework, ref.students);

        List<MarkIdForm> markIdForms = new ArrayList<>();
        ref.marks.forEach(item -> {
            MarkIdForm markIdForm = new MarkIdForm();
            markIdForm.setMark(String.valueOf(item.getMark()));
            markIdForm.setStudentLogin(item.getMarkId().getStudent().getLogin());
            markIdForm.setStudentName(item.getMarkId().getStudent().getName());
            markIdForm.setStudentSurname(item.getMarkId().getStudent().getSurname());
            markIdForms.add(markIdForm);
        });

        markForm.setMarkIdForms(markIdForms);

        modelAndView.addObject("homework", ref.homework);
        System.out.println(ref.homework.getTitle());

        return modelAndView;
    }

    @PostMapping(value = "/{id}")
    public RedirectView saveHomework(@ModelAttribute("markForm") MarkForm markForm, @PathVariable("id") String id) {

        System.out.println(markForm.getMarkIdForms().get(0).getMark());
        System.out.println(markForm.getMarkIdForms().get(0).getStudentLogin());

        var ref = new Object() {
            Student student;
            Homework homework;
        };
        homeworkService.getById(Long.parseLong(id)).ifPresent(item -> ref.homework = item);

        markForm.getMarkIdForms().forEach(itemForm -> {
            if (!itemForm.getMark().equals("")) {
                Optional.of(itemForm.getMark()).ifPresent(itemMark -> {
                    userService.getByLogin(itemForm.getStudentLogin()).ifPresent(itemStudent -> ref.student = (Student) itemStudent);
                    MarkId markId = new MarkId(ref.homework, ref.student);
                    Mark mark = new Mark();
                    mark.setMarkId(markId);
                    mark.setMark(Integer.parseInt(itemMark));
                    markService.save(mark);
                });
            }
        });

        return new RedirectView("/courses/" + ref.homework.getCourse().getId());
    }

}
