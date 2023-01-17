package by.home.jarward.web.service.impl;

import by.home.jarward.jar.repository.interfaces.UserJpaRepository;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserJpaRepository userJpaRepository;
}
