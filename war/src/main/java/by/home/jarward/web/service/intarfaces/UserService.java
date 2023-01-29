package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Teacher;
import by.home.jarward.jar.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> getById(Long id);
    Optional<User> getByLogin(String login);

    Optional<User> getByEmail(String email);

    void deleteAll();

    List<Teacher> getAllTeachers();

//    void deleteNotEnabledUsers();

}
