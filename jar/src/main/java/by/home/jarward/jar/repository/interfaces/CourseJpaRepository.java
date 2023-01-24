package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface CourseJpaRepository extends JpaRepository<Course, Long> {
    Page<Course> findByDateStartGreaterThan(LocalDate localDate, Pageable pageable);
    @Query("select c from Course c left join fetch c.students s")
    List<Course> findAllWithStudents();
    @Query("select c from Course c join fetch c.teachers t")
    List<Course> findAllWithTeachers();
    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.students s WHERE c.dateStart > :dateStart AND s.login <> :login OR s = null",
            countQuery = "SELECT c FROM Course c LEFT JOIN FETCH c.students s WHERE c.dateStart > :dateStart AND s.login <> :login OR s = null")
    Page<Course> findByDateStartGreaterThanAndLoginNot(@Param("dateStart") LocalDate localDate,
                                                       @Param("login") String login, Pageable pageable);
}
