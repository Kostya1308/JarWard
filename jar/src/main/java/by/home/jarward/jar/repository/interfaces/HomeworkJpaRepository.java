package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeworkJpaRepository extends JpaRepository<Homework, Long> {
    @Query(value = "select h from Homework h join fetch h.course where h.id =:id")
    List<Homework> findAllByCourseId(@Param("id") Long id);
}
