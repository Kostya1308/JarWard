package by.home.jarward.web.service.impl;

import by.home.jarward.jar.repository.interfaces.HomeworkJpaRepository;
import by.home.jarward.web.service.intarfaces.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkJpaRepository homeworkJpaRepository;
}
