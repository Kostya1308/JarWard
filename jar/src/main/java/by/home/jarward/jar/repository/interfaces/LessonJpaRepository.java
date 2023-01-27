package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonJpaRepository extends JpaRepository<Lesson, Long>{
    List<Lesson> findAllByCourse(Course course);


}
