package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;
import by.home.jarward.jar.entity.User;

import java.util.List;

public interface LessonService {
    Lesson save(Lesson lesson);
    List<Lesson> getAllByCourse(Course course);
    void deleteAll();
}
