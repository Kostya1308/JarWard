package by.home.jarward.web.service.impl;

import by.home.jarward.jar.entity.*;
import by.home.jarward.jar.enums.Role;
import by.home.jarward.jar.repository.interfaces.*;
import by.home.jarward.web.configuration.AppContext;
import by.home.jarward.web.service.intarfaces.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    LessonService lessonService;
    @Autowired
    MarkService markService;

    Teacher teacher = new Teacher();
    Student student1 = new Student();
    Student student2 = new Student();
    Course javaCoreCourse = new Course();
    Course javaEnterpriseCourse = new Course();
    Homework homework1 = new Homework();
    Lesson lesson1 = new Lesson();
    private static final char[] PASSWORD = "$2a$12$/64tQQnSBOLbq6fyhc0eB.gv6tpD2jkq3YXJi.Vfl8Io4QNtP2NO6".toCharArray();

    @Test
    public void fillBase() {
        teacher.setName("Yulia");
        teacher.setSurname("Ivanauskas");
        teacher.setLogin("Yulia");
        teacher.setPassword(PASSWORD);
        teacher.setEmail("Yulia@gmail.com");
        teacher.setRole(Role.TEACHER);
        userService.save(teacher);

        student1.setName("Kostya");
        student1.setSurname("Piskunou");
        student1.setLogin("Kostya1308");
        student1.setPassword(PASSWORD);
        student1.setEmail("kostya1308@gmail.com");
        student1.setRole(Role.STUDENT);
        userService.save(student1);

        student2.setName("Alex");
        student2.setSurname("Ivanov");
        student2.setLogin("Alex");
        student2.setPassword(PASSWORD);
        student2.setEmail("alex@gmail.com");
        student2.setRole(Role.STUDENT);
        userService.save(student2);

        javaCoreCourse.setTitle("Java Core");
        javaEnterpriseCourse.setTitle("Java Enterprise");
        courseService.save(javaCoreCourse);
        courseService.save(javaEnterpriseCourse);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        javaCoreCourse.setStudents(students);
        javaCoreCourse.setTeachers(teachers);
        courseService.save(javaCoreCourse);

        homework1.setCourse(javaCoreCourse);
        Mark mark = new Mark();
        MarkId markId = new MarkId();
        markId.setStudent(student1);
        markId.setHomework(homework1);
        mark.setMarkId(markId);
        mark.setMark(9);
        List<Mark> marks = new ArrayList<>();
        marks.add(mark);
        homework1.setMarks(marks);
        homeworkService.save(homework1);

        lesson1.setCourse(javaCoreCourse);
        lesson1.setStudents(students);
        lesson1.setTeachers(teachers);
        lessonService.save(lesson1);
    }

}