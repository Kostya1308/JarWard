package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.configuration.AppContextForTest;
import by.home.jarward.jar.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContextForTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserJpaRepositoryTest {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    @Qualifier("teacher")
    User teacher;
    @Autowired
    @Qualifier("student")
    User student1;
    @Autowired
    @Qualifier("student")
    User student2;
    @Autowired
    Course javaCoreCourse;
    @Autowired
    Course javaEnterpriseCourse;
    @Autowired
    Homework homework1;
    @Autowired
    Homework homework2;
    @Autowired
    Lesson lesson1;
    @Autowired
    Lesson lesson2;

    @BeforeAll
    public void init() {


        teacher.setName("Yulia");
        teacher.setSurname("Ivanauskas");
        teacher.setLogin("Yulia");
        teacher.setPassword("qwertyui".toCharArray());
        teacher.setEmail("Yulia@gmail.com");

        student1.setName("Kostya");
        student1.setSurname("Piskunou");
        student1.setLogin("Kostya1308");
        student1.setPassword("qwertyui".toCharArray());
        student1.setEmail("kostya1308@gmail.com");

        student2.setName("Alex");
        student2.setSurname("Ivanov");
        student2.setLogin("Alex");
        student2.setPassword("qwertyui".toCharArray());
        student2.setEmail("alex@gmail.com");


        javaCoreCourse.setTitle("Java Core");
        javaEnterpriseCourse.setTitle("Java Enterprise");


    }

    @Test
    public void createUserTest() {
        User user = new User();


        userJpaRepository.saveAndFlush(user);
    }

}