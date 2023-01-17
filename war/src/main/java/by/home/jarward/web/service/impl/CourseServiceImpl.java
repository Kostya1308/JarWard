package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.repository.interfaces.CourseJpaRepository;
import by.home.jarward.web.service.intarfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Override
    public Course save(Course course) {
        return courseJpaRepository.save(course);
    }
}
