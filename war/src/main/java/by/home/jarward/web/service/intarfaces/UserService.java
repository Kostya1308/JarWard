package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> getByLogin(String login);

    Optional<User> getByEmail(String email);

    void deleteNotEnabledUsers();

}
