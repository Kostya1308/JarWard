package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Lesson;
import by.home.jarward.jar.repository.interfaces.LessonJpaRepository;
import by.home.jarward.web.service.intarfaces.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonJpaRepository lessonJpaRepository;

    @Override
    public Lesson save(Lesson lesson) {
        return lessonJpaRepository.save(lesson);
    }
}
