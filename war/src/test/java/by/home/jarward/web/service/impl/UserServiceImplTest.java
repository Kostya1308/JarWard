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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    PasswordEncoder passwordEncoder;

    Teacher teacher = new Teacher();
    Student student1 = new Student();
    Student student2 = new Student();
    Course javaCoreCourse = new Course();
    Course javaEnterpriseCourse = new Course();
    Course dataBasecourse = new Course();
    Course javaScriptCourse = new Course();
    Homework homework1 = new Homework();
    Lesson lesson1 = new Lesson();
    Lesson lesson2 = new Lesson();
    Lesson lesson3 = new Lesson();
    private static final String PASSWORD = "qwertyui";

    @Test
    public void getMarks(){
        List<Homework> homeworkList = homeworkService.getAllByCourseId(39L);
        Optional<User> student = userService.getByLogin("Kostya");
        List<Mark> marks = markService.getByHomeworksAndStudent(homeworkList, student.get());
        System.out.println(marks.size());
    }

    @Test
    public void getCourseByTeacherLogin(){
        Pageable pageWithThreeElements = PageRequest.of(0, 3);

        Page<Course> courses = courseService.getAllByTeacherLogin("James", pageWithThreeElements);
        System.out.println(courses.get().toList().size());
    }


//    @Test
//    public void fillBase() {
//        teacher.setName("Yulia");
//        teacher.setSurname("Ivanauskas");
//        teacher.setLogin("Yulia");
//        teacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
//        teacher.setEmail("Yulia@gmail.com");
//        teacher.setRole(Role.TEACHER);
//        teacher.setEnabled(true);
//        userService.save(teacher);
//
//        student1.setName("Kostya");
//        student1.setSurname("Piskunou");
//        student1.setLogin("Kostya1308");
//        student1.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
//        student1.setEmail("kostya1308@gmail.com");
//        student1.setRole(Role.STUDENT);
//        student1.setEnabled(true);
//        userService.save(student1);
//
//        student2.setName("Alex");
//        student2.setSurname("Ivanov");
//        student2.setLogin("Alex");
//        student2.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
//        student2.setEmail("alex@gmail.com");
//        student2.setRole(Role.STUDENT);
//        student2.setEnabled(true);
//        userService.save(student2);
//
//        javaCoreCourse.setTitle("Java Core");
//        javaCoreCourse.setDateStart(LocalDate.of(2023, 6, 6));
//        javaCoreCourse.setDateEnd(LocalDate.of(2023, 8, 6));
//        javaCoreCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
//        javaEnterpriseCourse.setTitle("Java Enterprise");
//        javaEnterpriseCourse.setDateStart(LocalDate.of(2023, 6, 6));
//        javaEnterpriseCourse.setDateEnd(LocalDate.of(2023, 8, 6));
//        javaEnterpriseCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
//        dataBasecourse.setTitle("Database");
//        dataBasecourse.setDateStart(LocalDate.of(2023, 6, 6));
//        dataBasecourse.setDateEnd(LocalDate.of(2023, 8, 6));
//        dataBasecourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
//        javaScriptCourse.setTitle("JavaScript");
//        javaScriptCourse.setDateStart(LocalDate.of(2023, 6, 6));
//        javaScriptCourse.setDateEnd(LocalDate.of(2023, 8, 6));
//        javaScriptCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
//
//        courseService.save(javaCoreCourse);
//        courseService.save(javaEnterpriseCourse);
//        courseService.save(dataBasecourse);
//        courseService.save(javaScriptCourse);
//
//        List<Student> students = new ArrayList<>();
//        students.add(student1);
//        List<Teacher> teachers = new ArrayList<>();
//        teachers.add(teacher);
//        javaCoreCourse.setStudents(students);
//        javaCoreCourse.setTeachers(teachers);
//        courseService.save(javaCoreCourse);
//
//        homework1.setCourse(javaCoreCourse);
//        Mark mark = new Mark();
//        MarkId markId = new MarkId();
//        markId.setStudent(student1);
//        markId.setHomework(homework1);
//        mark.setMarkId(markId);
//        mark.setMark(9);
//        List<Mark> marks = new ArrayList<>();
//        marks.add(mark);
//        homework1.setMarks(marks);
//        homeworkService.save(homework1);
//
//        lesson1.setCourse(javaCoreCourse);
//        lesson1.setStudents(students);
//        lesson1.setTeachers(teachers);
//        lesson1.setDateTimeStart(LocalDateTime.of(2023, 6, 1, 18, 0));
//        lessonService.save(lesson1);
//
//        lesson2.setCourse(javaCoreCourse);
//        lesson2.setStudents(students);
//        lesson2.setTeachers(teachers);
//        lesson2.setDateTimeStart(LocalDateTime.of(2023, 6, 5, 18, 0));
//        lessonService.save(lesson2);
//
//        lesson3.setCourse(javaCoreCourse);
//        lesson3.setStudents(students);
//        lesson3.setTeachers(teachers);
//        lesson3.setDateTimeStart(LocalDateTime.of(2023, 6, 10, 18, 0));
//        lessonService.save(lesson3);
//    }
    @Test
    public void init(){
        courseService.getAll();
    }

}