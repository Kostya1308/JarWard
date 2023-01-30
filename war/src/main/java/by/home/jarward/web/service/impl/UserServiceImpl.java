package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Teacher;
import by.home.jarward.jar.entity.User;
import by.home.jarward.jar.repository.interfaces.UserJpaRepository;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userJpaRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return userJpaRepository.findTeachers();
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAll();
    }

    //    @Override
//    @Scheduled(fixedRate = 100000)
//    public void deleteNotEnabledUsers() {
//        LocalDateTime now = LocalDateTime.now();
//        List<User> users = userJpaRepository.findByEnabledFalseAndDateTimeCreateLessThan(now.plusHours(1L));
//        userJpaRepository.deleteAllInBatch(users);
//    }

}
