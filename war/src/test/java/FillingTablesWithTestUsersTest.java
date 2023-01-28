import by.home.jarward.jar.entity.*;
import by.home.jarward.jar.enums.Gender;
import by.home.jarward.jar.enums.Role;
import by.home.jarward.web.configuration.AppContext;
import by.home.jarward.web.service.intarfaces.*;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@Slf4j
public class FillingTablesWithTestUsersTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private MarkService markService;
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

    private final Course javaFundamentalsCourse = new Course();
    private final Course javaEnterpriseCourse = new Course();
    private final Course dataBaseCourse = new Course();
    private final Course javaScriptCourse = new Course();
    private final Course smmCourse = new Course();

    private final Homework javaFundamentalsHomework1 = new Homework();
    private final Homework javaFundamentalsHomework2 = new Homework();
    private final Homework javaFundamentalsHomework3 = new Homework();

    private final Lesson javaFundamentalsLesson1 = new Lesson();
    private final Lesson javaFundamentalsLesson2 = new Lesson();
    private final Lesson javaFundamentalsLesson3 = new Lesson();

    private final Mark markHomework1 = new Mark();
    private final Mark markHomework2 = new Mark();


    private static final String PASSWORD = "qwertyui";

    private final File file = new File("src/main/resources/course_description.properties");
    private final Properties properties = new Properties();

    @Test
    public void initDataBase() {
        courseService.getAll();
    }

    @Test
    public void clearDataBase(){
        markService.deleteAll();
        lessonService.deleteAll();
        homeworkService.deleteAll();
        courseService.deleteAll();
        userService.deleteAll();
    }

    @Test
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
        jamesGoslingTeacher.setLanguage("english");
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
        herbertSchildtTeacher.setLanguage("english");
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
        kathySierraTeacher.setLanguage("english");
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
        joshuaBlochTeacher.setLanguage("english");
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
        inputStream = new FileInputStream("src/main/resources/photos/cat.jpg");
        catStudent.setPhoto(inputStream.readAllBytes());
        inputStream.close();
        userService.save(catStudent);

        javaFundamentalsCourse.setTitle("Java Fundamentals");
        javaFundamentalsCourse.setDateStart(LocalDate.of(2023, 1, 16));
        javaFundamentalsCourse.setDateEnd(LocalDate.of(2023, 2, 6));
        javaFundamentalsCourse.setDescription(properties.getProperty("java_fundamentals"));
        courseService.save(javaFundamentalsCourse);

        javaEnterpriseCourse.setTitle("Java Enterprise");
        javaEnterpriseCourse.setDateStart(LocalDate.of(2023, 2, 6));
        javaEnterpriseCourse.setDateEnd(LocalDate.of(2023, 2, 20));
        javaEnterpriseCourse.setDescription(properties.getProperty("java_enterprise"));
        courseService.save(javaEnterpriseCourse);

        dataBaseCourse.setTitle("Data Base");
        dataBaseCourse.setDateStart(LocalDate.of(2023, 2, 20));
        dataBaseCourse.setDateEnd(LocalDate.of(2023, 3, 6));
        dataBaseCourse.setDescription(properties.getProperty("database"));
        courseService.save(dataBaseCourse);

        javaScriptCourse.setTitle("Java Script");
        javaScriptCourse.setDateStart(LocalDate.of(2023, 3, 6));
        javaScriptCourse.setDateEnd(LocalDate.of(2023, 3, 20));
        javaScriptCourse.setDescription(properties.getProperty("javascript"));
        courseService.save(javaScriptCourse);

        smmCourse.setTitle("SMM");
        smmCourse.setDateStart(LocalDate.of(2023, 3, 20));
        smmCourse.setDateEnd(LocalDate.of(2023, 4, 6));
        smmCourse.setDescription(properties.getProperty("smm"));
        courseService.save(smmCourse);

        javaFundamentalsHomework1.setTitle("Variables");
        javaFundamentalsHomework1.setDeadLine(LocalDate.of(2023, 1, 20));
        javaFundamentalsHomework1.setCourse(javaFundamentalsCourse);
        homeworkService.save(javaFundamentalsHomework1);

        javaFundamentalsHomework2.setTitle("Generics");
        javaFundamentalsHomework2.setDeadLine(LocalDate.of(2023, 1, 25));
        javaFundamentalsHomework2.setCourse(javaFundamentalsCourse);
        homeworkService.save(javaFundamentalsHomework2);

        javaFundamentalsHomework3.setTitle("Stream API");
        javaFundamentalsHomework3.setDeadLine(LocalDate.of(2023, 1, 30));
        javaFundamentalsHomework3.setCourse(javaFundamentalsCourse);
        homeworkService.save(javaFundamentalsHomework3);

        javaFundamentalsLesson1.setTitle("Lesson 1");
        javaFundamentalsLesson1.setDateStart(LocalDate.of(2023, 1, 16));
        javaFundamentalsLesson1.setDateEnd(LocalDate.of(2023, 1, 16));
        javaFundamentalsLesson1.setTimeStart(LocalTime.of(18,0));
        javaFundamentalsLesson1.setTimeEnd(LocalTime.of(21,0));
        javaFundamentalsLesson1.setCourse(javaFundamentalsCourse);
        lessonService.save(javaFundamentalsLesson1);

        javaFundamentalsLesson2.setTitle("Lesson 2");
        javaFundamentalsLesson2.setDateStart(LocalDate.of(2023, 1, 31));
        javaFundamentalsLesson2.setDateEnd(LocalDate.of(2023, 1, 31));
        javaFundamentalsLesson2.setTimeStart(LocalTime.of(18,0));
        javaFundamentalsLesson2.setTimeEnd(LocalTime.of(21,0));
        javaFundamentalsLesson2.setCourse(javaFundamentalsCourse);
        lessonService.save(javaFundamentalsLesson2);

        javaFundamentalsLesson3.setTitle("Lesson 3");
        javaFundamentalsLesson3.setDateStart(LocalDate.of(2023, 2, 5));
        javaFundamentalsLesson3.setDateEnd(LocalDate.of(2023, 2, 5));
        javaFundamentalsLesson3.setTimeStart(LocalTime.of(18,0));
        javaFundamentalsLesson3.setTimeEnd(LocalTime.of(21,0));
        javaFundamentalsLesson3.setCourse(javaFundamentalsCourse);
        lessonService.save(javaFundamentalsLesson3);

        javaFundamentalsCourse.setStudents(List.of(kostyaStudent, nastyaStudent, catStudent));
        javaFundamentalsCourse.setTeacher(jamesGoslingTeacher);
        courseService.save(javaFundamentalsCourse);


        MarkId markId1 = new MarkId();
        markId1.setStudent(kostyaStudent);
        markId1.setHomework(javaFundamentalsHomework1);
        markHomework1.setMarkId(markId1);
        markHomework1.setMark(9);
        javaFundamentalsHomework1.setMarks(List.of(markHomework1));
        homeworkService.save(javaFundamentalsHomework1);

        MarkId markId2 = new MarkId();
        markId2.setStudent(kostyaStudent);
        markId2.setHomework(javaFundamentalsHomework2);
        markHomework2.setMarkId(markId2);
        markHomework2.setMark(8);
        javaFundamentalsHomework2.setMarks(List.of(markHomework2));
        homeworkService.save(javaFundamentalsHomework2);

        javaFundamentalsLesson1.setStudents(List.of(kostyaStudent, nastyaStudent));
        lessonService.save(javaFundamentalsLesson1);

        javaFundamentalsCourse.setTeacher(jamesGoslingTeacher);
        javaEnterpriseCourse.setTeacher(joshuaBlochTeacher);
        dataBaseCourse.setTeacher(kathySierraTeacher);
        javaScriptCourse.setTeacher(herbertSchildtTeacher);
        smmCourse.setTeacher(nastyaTeacher);

        courseService.save(javaFundamentalsCourse);
        courseService.save(javaEnterpriseCourse);
        courseService.save(dataBaseCourse);
        courseService.save(javaScriptCourse);
    }
}
