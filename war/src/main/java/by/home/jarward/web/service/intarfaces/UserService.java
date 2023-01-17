package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByLogin(String login);
}
