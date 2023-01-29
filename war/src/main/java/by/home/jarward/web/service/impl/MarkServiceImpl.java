package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.*;
import by.home.jarward.jar.repository.interfaces.MarkJpaRepository;
import by.home.jarward.web.service.intarfaces.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    MarkJpaRepository markJpaRepository;

    @Override
    public Mark save(Mark mark) {
        return markJpaRepository.save(mark);
    }

    @Override
    public Optional<Mark> getById(MarkId markId) {
        return markJpaRepository.findById(markId);
    }

    @Override
    public List<Mark> getByHomeworksAndStudent(List<Homework> homeworkList, User student) {
        List<Mark> marks = new ArrayList<>();
        for (Homework itemHomework : homeworkList) {
            getById(new MarkId(itemHomework, ((Student) student)))
                    .ifPresent(marks::add);
        }
        return marks;
    }

    @Override
    public List<Mark> getByHomeworkAndStudents(Homework homework, List<Student> students) {
        List<Mark> marks = new ArrayList<>();
        for (Student itemStudent : students) {
            getById(new MarkId(homework, (itemStudent)))
                    .ifPresentOrElse(marks::add, () -> {
                        MarkId markId = new MarkId(homework, itemStudent);
                        Mark mark = new Mark();
                        mark.setMarkId(markId);
                        marks.add(mark);
                    });
        }
        return marks;
    }

    @Override
    public void deleteAll() {
        markJpaRepository.deleteAll();
    }
}
