package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.User;
import by.home.jarward.jar.repository.interfaces.UserJpaRepository;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userJpaRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
