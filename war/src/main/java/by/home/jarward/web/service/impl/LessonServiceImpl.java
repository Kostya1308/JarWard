package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;
import by.home.jarward.jar.repository.interfaces.LessonJpaRepository;
import by.home.jarward.web.service.intarfaces.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonJpaRepository lessonJpaRepository;

    @Override
    public Lesson save(Lesson lesson) {
        return lessonJpaRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAllByCourse(Course course) {
        return lessonJpaRepository.findAllByCourse(course);
    }

    @Override
    public List<Lesson> findAllByStudentLoginByCourseId(String login, String courseId) {
        return lessonJpaRepository.findAllByStudentLoginByCourseId(login, courseId);
    }

    @Override
    public void deleteAll() {
        lessonJpaRepository.deleteAll();


    }
}
