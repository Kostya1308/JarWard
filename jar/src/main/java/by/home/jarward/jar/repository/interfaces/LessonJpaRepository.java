package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.Course;
import by.home.jarward.jar.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonJpaRepository extends JpaRepository<Lesson, Long>{
    List<Lesson> findAllByCourse(Course course);

    @Query("select l from Lesson l right join fetch l.students s where s.login =:login and l.course.id =:courseId")
    List<Lesson> findAllByStudentLoginByCourseId(@Param("login") String login, @Param("courseId") String courseId);

}
