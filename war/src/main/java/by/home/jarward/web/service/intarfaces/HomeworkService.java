package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeworkService {
    Homework save(Homework homework);
    List<Homework> getAllByCourseId(Long id);
    void deleteAll();
}
