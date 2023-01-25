package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);
    Page<Course> getAllByDateStartGreaterThan(LocalDate localDate, Pageable pageable);
    List<Course> getAllWithStudents();
    List<Course> getAll();
    Page<Course> getAllByDateStartGreaterThanByLoginNot(LocalDate localDate, String login, Pageable pageable);
    Page<Course> getAllByDateEndGreaterThanAndLogin(LocalDate localDate, String login, Pageable pageable);
    Optional<Course> getByTitleWithStudents(String title);

}
