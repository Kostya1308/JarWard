package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;


import java.util.List;
import java.util.Optional;

public interface LessonService {

    Lesson save(Lesson lesson);

    Optional<Lesson> getById(Long id);

    List<Lesson> getAllByCourse(Course course);

    List<Lesson> findAllByStudentLoginByCourseId(String login, String courseId);

    void deleteAll();

}
