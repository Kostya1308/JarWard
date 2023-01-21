package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.configuration.AppContextForTest;
import by.home.jarward.jar.entity.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContextForTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserJpaRepositoryTest {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    CourseJpaRepository courseJpaRepository;
    @Autowired
    HomeworkJpaRepository homeworkJpaRepository;
    @Autowired
    LessonJpaRepository lessonJpaRepository;
    @Autowired
    MarkJpaRepository markJpaRepository;

    Teacher teacher = new Teacher();
    Student student1 = new Student();
    Student student2 = new Student();
    Course javaCoreCourse = new Course();
    Course javaEnterpriseCourse = new Course();
    Homework homework1 = new Homework();
    Lesson lesson1 = new Lesson();

    @Test
    public void checkAverageMarkTest() {
        MarkId markId = new MarkId();
        markId.setHomework(homework1);
        markId.setStudent(student1);
        Optional<Mark> mark = markJpaRepository.findById(markId);

        mark.ifPresent(markItem -> Assertions.assertEquals(markItem.getMark(), 9));
    }

    @Test
    public void getListNotEnabledUsersTest(){
        List<User> users = userJpaRepository.findByEnabledFalseAndDateTimeCreateLessThan(LocalDateTime.now().plusDays(1L));
        System.out.println(users);
    }



    @BeforeAll
    public void fillBase() {

        teacher.setName("Yulia");
        teacher.setSurname("Ivanauskas");
        teacher.setLogin("Yulia");
        teacher.setPassword("qwertyui".toCharArray());
        teacher.setEmail("Yulia@gmail.com");
        teacher.setEnabled(false);
        userJpaRepository.save(teacher);

        student1.setName("Kostya");
        student1.setSurname("Piskunou");
        student1.setLogin("Kostya1308");
        student1.setPassword("qwertyui".toCharArray());
        student1.setEmail("kostya1308@gmail.com");
        student1.setEnabled(false);
        userJpaRepository.save(student1);

        student2.setName("Alex");
        student2.setSurname("Ivanov");
        student2.setLogin("Alex");
        student2.setPassword("qwertyui".toCharArray());
        student2.setEmail("alex@gmail.com");
        student2.setEnabled(true);
        userJpaRepository.save(student2);

        javaCoreCourse.setTitle("Java Core");
        javaEnterpriseCourse.setTitle("Java Enterprise");
        courseJpaRepository.save(javaCoreCourse);
        courseJpaRepository.save(javaEnterpriseCourse);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        javaCoreCourse.setStudents(students);
        javaCoreCourse.setTeachers(teachers);
        courseJpaRepository.save(javaCoreCourse);

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
        homeworkJpaRepository.save(homework1);

        lesson1.setCourse(javaCoreCourse);
        lesson1.setStudents(students);
        lesson1.setTeachers(teachers);
        lessonJpaRepository.save(lesson1);
    }

    @AfterAll
    public void clearBase() {
        lessonJpaRepository.deleteAll();
        homeworkJpaRepository.deleteAll();
        courseJpaRepository.deleteAll();
        userJpaRepository.deleteAll();
    }
}