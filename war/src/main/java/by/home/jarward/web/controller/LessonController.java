package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;
import by.home.jarward.jar.entity.Student;
import by.home.jarward.web.service.intarfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping(value = "/lessons")
public class LessonController {
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
    public ModelAndView getLessonPage(@RequestParam("id") String idLesson) {

        ModelAndView modelAndView = new ModelAndView("lesson");
        var ref = new Object() {
            Course course;
        };
        Optional<Lesson> lesson = lessonService.getById(Long.parseLong(idLesson));
        lesson.flatMap(itemLesson ->
                        courseService.getByIdWithStudents(itemLesson.getCourse().getId()))
                .ifPresent(itemCourse -> ref.course = itemCourse);
        modelAndView.addObject("students", ref.course.getStudents());
        lesson.ifPresent(item -> modelAndView.addObject("lesson", item));

        return modelAndView;
    }

    @PostMapping
    public RedirectView saveLesson(@RequestParam("id") String idLesson, @RequestParam("presentStudents") String[] presentStudents) {
        Optional<Lesson> lesson = lessonService.getById(Long.parseLong(idLesson));
        List<Student> students = new ArrayList<>();
        AtomicReference<String> courseId = new AtomicReference<>();
        Arrays.stream(presentStudents)
                .forEach(item -> userService.getById(Long.parseLong(item))
                        .ifPresent(itemUser -> students.add((Student) itemUser)));

        lesson.ifPresent(itemLesson -> {
            itemLesson.setStudents(students);
            lessonService.save(itemLesson);
            courseId.set(String.valueOf(itemLesson.getCourse().getId()));
        });

        return new RedirectView("/courses/" + courseId);

    }
}
