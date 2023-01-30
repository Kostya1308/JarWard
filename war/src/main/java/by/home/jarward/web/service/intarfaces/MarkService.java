package by.home.jarward.web.service.intarfaces;

import by.home.jarward.jar.entity.*;

import java.util.List;
import java.util.Optional;

public interface MarkService {

    Mark save(Mark mark);

    Optional<Mark> getById(MarkId markId);

    List<Mark> getByHomeworksAndStudent(List<Homework> homeworkList, User student);

    List<Mark> getByHomeworkAndStudents(Homework homework, List<Student> student);

    void deleteAll();

}
