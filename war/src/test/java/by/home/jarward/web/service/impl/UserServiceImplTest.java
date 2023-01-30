package by.home.jarward.web.service.impl;

import by.home.jarward.jar.configuration.AppContextForTest;
import by.home.jarward.jar.entity.*;
import by.home.jarward.jar.enums.Gender;
import by.home.jarward.jar.enums.Role;
import by.home.jarward.web.service.intarfaces.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContextForTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Teacher jamesGoslingTeacher = new Teacher();
    private final Teacher herbertSchildtTeacher = new Teacher();
    private final Teacher kathySierraTeacher = new Teacher();
    private final Teacher joshuaBlochTeacher = new Teacher();
    private final Teacher nastyaTeacher = new Teacher();

    private final Student kostyaStudent = new Student();
    private final Student nastyaStudent = new Student();
    private final Student catStudent = new Student();

    private static final String PASSWORD = "qwertyui";

    private final File file = new File("src/main/resources/course_description.properties");
    private final Properties properties = new Properties();


    @Test
    void getByLogin() {
        Optional<User> user = userService.getByLogin("Kostya");
        user.ifPresent(item -> Assertions.assertEquals(item.getLogin(), "Kostya"));
    }

    @Test
    void getByEmail() {
    }

    @Test
    void getAllTeachers() {
    }

    @BeforeAll
    public void fillDataBase() throws IOException {
        properties.load(new FileReader(file));
        jamesGoslingTeacher.setName("James");
        jamesGoslingTeacher.setSurname("Gosling");
        jamesGoslingTeacher.setLogin("James");
        jamesGoslingTeacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        jamesGoslingTeacher.setEmail("jamesgosling@gmail.com");
        jamesGoslingTeacher.setRole(Role.TEACHER);
        jamesGoslingTeacher.setGender(Gender.MALE);
        jamesGoslingTeacher.setEnabled(true);
        jamesGoslingTeacher.setDateOfBirth(LocalDate.of(1955, 5, 19));
        jamesGoslingTeacher.setLanguage("en");
        InputStream inputStream = new FileInputStream("src/main/resources/photos/gosling.jpg");
        jamesGoslingTeacher.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(jamesGoslingTeacher);

        herbertSchildtTeacher.setName("Herbert");
        herbertSchildtTeacher.setSurname("Schildt");
        herbertSchildtTeacher.setLogin("Herbert");
        herbertSchildtTeacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        herbertSchildtTeacher.setEmail("herbertschildt@gmail.com");
        herbertSchildtTeacher.setRole(Role.TEACHER);
        herbertSchildtTeacher.setGender(Gender.MALE);
        herbertSchildtTeacher.setEnabled(true);
        herbertSchildtTeacher.setDateOfBirth(LocalDate.of(1951, 2, 28));
        herbertSchildtTeacher.setLanguage("en");
        inputStream = new FileInputStream("src/main/resources/photos/herbert.jpg");
        herbertSchildtTeacher.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(herbertSchildtTeacher);

        kathySierraTeacher.setName("Kathy");
        kathySierraTeacher.setSurname("Sierra");
        kathySierraTeacher.setLogin("Kathy");
        kathySierraTeacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        kathySierraTeacher.setEmail("kathysierra@gmail.com");
        kathySierraTeacher.setRole(Role.TEACHER);
        kathySierraTeacher.setGender(Gender.FEMALE);
        kathySierraTeacher.setEnabled(true);
        kathySierraTeacher.setDateOfBirth(LocalDate.of(1957, 2, 12));
        kathySierraTeacher.setLanguage("en");
        inputStream = new FileInputStream("src/main/resources/photos/sierra.jpeg");
        kathySierraTeacher.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(kathySierraTeacher);

        joshuaBlochTeacher.setName("Joshua");
        joshuaBlochTeacher.setSurname("Bloch");
        joshuaBlochTeacher.setLogin("Joshua");
        joshuaBlochTeacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        joshuaBlochTeacher.setEmail("joshuabloch@gmail.com");
        joshuaBlochTeacher.setRole(Role.TEACHER);
        joshuaBlochTeacher.setGender(Gender.MALE);
        joshuaBlochTeacher.setEnabled(true);
        joshuaBlochTeacher.setDateOfBirth(LocalDate.of(1961, 8, 28));
        joshuaBlochTeacher.setLanguage("English");
        inputStream = new FileInputStream("src/main/resources/photos/joshua.jpeg");
        joshuaBlochTeacher.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(jamesGoslingTeacher);

        nastyaTeacher.setName("Nastya");
        nastyaTeacher.setSurname("Glushenok");
        nastyaTeacher.setLogin("Nastya");
        nastyaTeacher.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        nastyaTeacher.setEmail("nastya@gmail.com");
        nastyaTeacher.setRole(Role.TEACHER);
        nastyaTeacher.setEnabled(true);
        nastyaTeacher.setDateOfBirth(LocalDate.of(1992, 9, 10));
        nastyaTeacher.setGender(Gender.FEMALE);
        nastyaTeacher.setLanguage("English");
        inputStream = new FileInputStream("src/main/resources/photos/nastya.jpg");
        nastyaTeacher.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(nastyaTeacher);

        kostyaStudent.setName("Constantine");
        kostyaStudent.setSurname("Piskunov");
        kostyaStudent.setLogin("Kostya");
        kostyaStudent.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        kostyaStudent.setEmail("kostya@gmail.com");
        kostyaStudent.setRole(Role.STUDENT);
        kostyaStudent.setEnabled(true);
        kostyaStudent.setDateOfBirth(LocalDate.of(1993, 3, 28));
        kostyaStudent.setGender(Gender.MALE);
        kostyaStudent.setLanguage("English");
        inputStream = new FileInputStream("src/main/resources/photos/kostya.jpeg");
        kostyaStudent.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(kostyaStudent);

        nastyaStudent.setName("Nastya");
        nastyaStudent.setSurname("Glushenok");
        nastyaStudent.setLogin("Nastya1212");
        nastyaStudent.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        nastyaStudent.setEmail("nastya1212@gmail.com");
        nastyaStudent.setRole(Role.STUDENT);
        nastyaStudent.setEnabled(true);
        nastyaStudent.setDateOfBirth(LocalDate.of(1992, 9, 10));
        nastyaStudent.setGender(Gender.FEMALE);
        nastyaStudent.setLanguage("French");
        inputStream = new FileInputStream("src/main/resources/photos/nastya.jpg");
        nastyaStudent.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(nastyaStudent);

        catStudent.setName("Customs");
        catStudent.setSurname("Piskunov");
        catStudent.setLogin("Customs");
        catStudent.setPassword((passwordEncoder.encode(PASSWORD)).toCharArray());
        catStudent.setEmail("meow@gmail.com");
        catStudent.setRole(Role.STUDENT);
        catStudent.setEnabled(true);
        catStudent.setDateOfBirth(LocalDate.of(2022, 4, 15));
        catStudent.setGender(Gender.MALE);
        catStudent.setLanguage("English");
        inputStream = new FileInputStream("src/main/resources/photos/cat.jpg");
        catStudent.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(catStudent);
    }

    @Test
    public void clearDataBase(){
        userService.deleteAll();
    }
}