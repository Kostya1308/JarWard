package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.entity.Lesson;
import by.home.jarward.jar.entity.Mark;
import by.home.jarward.jar.entity.User;
import by.home.jarward.web.service.intarfaces.HomeworkService;
import by.home.jarward.web.service.intarfaces.LessonService;
import by.home.jarward.web.service.intarfaces.MarkService;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping(value = "/statistic")
public class UserStatisticRestController {
    @Autowired
    UserService userService;
    @Autowired
    LessonService lessonService;
    @Autowired
    MarkService markService;
    @Autowired
    HomeworkService homeworkService;

    @GetMapping(value = "/present")
    public ResponseEntity<String> getQuantityLessons(@RequestParam("login") String login,
                                                     @RequestParam("courseId") String courseId) {

        List<Lesson> lessons = lessonService.findAllByStudentLoginByCourseId(login, courseId);
        return new ResponseEntity<>(String.valueOf(lessons.size()), HttpStatus.OK);
    }

    @GetMapping(value = "/average-mark")
    public ResponseEntity<String> getAverageMark(@RequestParam("login") String login,
                                                 @RequestParam("courseId") String courseId) {

        List<Homework> homeworkList = homeworkService.getAllByCourseId(Long.parseLong(courseId));
        Optional<User> user = userService.getByLogin(login);
        var ref = new Object() {
            String average;
        };
        user.ifPresent(itemUser -> {
                    List<Mark> marks = markService.getByHomeworksAndStudent(homeworkList, itemUser);
                    OptionalDouble averageDouble = marks.stream().map(Mark::getMark).mapToInt(itemMark -> itemMark)
                            .average();
                    if (averageDouble.isPresent()) {
                        ref.average = String.valueOf(averageDouble.getAsDouble());
                    } else {
                        ref.average = "0.0";
                    }
                }
        );

        return new ResponseEntity<>(ref.average, HttpStatus.OK);
    }
}
