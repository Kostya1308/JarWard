package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.repository.interfaces.HomeworkJpaRepository;
import by.home.jarward.web.service.intarfaces.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkJpaRepository homeworkJpaRepository;

    @Override
    public Homework save(Homework homework) {
        return homeworkJpaRepository.save(homework);
    }

    @Override
    public List<Homework> getAllByCourseId(Long id) {
        return homeworkJpaRepository.findAllByCourseId(id);
    }

    @Override
    public void deleteAll() {
        homeworkJpaRepository.deleteAll();
    }
}
