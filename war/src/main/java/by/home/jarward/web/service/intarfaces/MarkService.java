package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.Homework;
import by.home.jarward.jar.entity.Mark;
import by.home.jarward.jar.entity.MarkId;
import by.home.jarward.jar.entity.User;

import java.util.List;
import java.util.Optional;

public interface MarkService {

    Mark save(Mark mark);
    Optional<Mark> getById(MarkId markId);

    List<Mark> getByHomeworksAndStudent(List<Homework> homeworkList, User student);

}
