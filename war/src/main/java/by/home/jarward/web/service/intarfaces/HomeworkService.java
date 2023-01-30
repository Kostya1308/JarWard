package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Homework;

import java.util.List;
import java.util.Optional;

public interface HomeworkService {
    Homework save(Homework homework);

    Optional<Homework> getById(Long id);

    List<Homework> getAllByCourseId(Long id);

    void deleteAll();
}
