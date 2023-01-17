package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}
