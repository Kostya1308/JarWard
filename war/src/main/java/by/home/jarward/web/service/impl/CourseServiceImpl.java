package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.repository.interfaces.CourseJpaRepository;
import by.home.jarward.web.service.intarfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Override
    public Course save(Course course) {
        return courseJpaRepository.save(course);
    }

    @Override
    public Page<Course> getAllByDateStartGreaterThan(LocalDate localDate, Pageable pageable) {
        return courseJpaRepository.findByDateStartGreaterThan(localDate, pageable);
    }

    @Override
    public List<Course> getAllWithStudents() {
        return courseJpaRepository.findAllWithStudents();
    }

    @Override
    public List<Course> getAll() {
        return courseJpaRepository.findAll();
    }

    @Override
    public Page<Course> getAllByDateStartGreaterThanByLoginNot(LocalDate localDate, String login, Pageable pageable) {
        return courseJpaRepository.findByDateStartGreaterThanAndLoginNot(localDate, login, pageable);
    }

    @Override
    public Page<Course> getAllByDateEndGreaterThanAndLogin(LocalDate localDate, String login, Pageable pageable) {
        return courseJpaRepository.findByDateEndGreaterThanAndLogin(localDate, login, pageable);
    }

    public Optional<Course> getByIdWithStudents(Long id){
        return courseJpaRepository.findByIdWithStudents(id);
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseJpaRepository.findById(id);
    }
}
