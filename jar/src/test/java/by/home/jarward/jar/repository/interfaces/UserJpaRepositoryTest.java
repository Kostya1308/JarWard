package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.configuration.AppContextForTest;
import by.home.jarward.jar.entity.*;
import by.home.jarward.jar.enums.Role;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    Course dataBasecourse = new Course();
    Course javaScriptCourse = new Course();
    Homework homework1 = new Homework();
    Lesson lesson1 = new Lesson();
    Lesson lesson2 = new Lesson();
    Lesson lesson3 = new Lesson();

    private static final String PASSWORD = "qwertyui";

    @Test
    public void checkAverageMarkTest() {
        MarkId markId = new MarkId();
        markId.setHomework(homework1);
        markId.setStudent(student1);
        Optional<Mark> mark = markJpaRepository.findById(markId);

        mark.ifPresent(markItem -> Assertions.assertEquals(markItem.getMark(), 9));
    }

    @Test
    public void getListNotEnabledUsersTest() {
        List<User> users = userJpaRepository.findByEnabledFalseAndDateTimeCreateLessThan(LocalDateTime.now().plusDays(1L));
        System.out.println(users);
    }

    @Test
    public void getCourses_1() {
        Pageable pageWithThreeElements = PageRequest.of(0, 3);
        Page<Course> coursesPage =
                courseJpaRepository.findByDateStartGreaterThanAndLoginNot(LocalDate.now(), "Kostya1308", pageWithThreeElements);
        System.out.println(coursesPage.get().toList().size());
    }

    @Test
    public void getCourses_2() {
        Pageable pageWithThreeElements = PageRequest.of(0, 3);
        Page<Course> coursesPage =
                courseJpaRepository.findByDateEndGreaterThanAndLogin(LocalDate.now(), "Kostya1308", pageWithThreeElements);
        System.out.println(coursesPage.get().toList().size());
    }

    @Test
    public void getHomework() {
        List<Homework> homeworkList = homeworkJpaRepository.findAllByCourseId(1L);
        System.out.println(homeworkList.size());
    }

    @Test
    public void getMark() {
        Optional<Homework> homeworkById = homeworkJpaRepository.findById(1L);
        Optional<User> studentById = userJpaRepository.findById(2L);
        Optional<Mark> mark = markJpaRepository.findById(new MarkId(homeworkById.get(), (Student) studentById.get()));
        System.out.println(mark.get().getMark());
    }

    @Test
    public void getLessonsTest(){
        Optional<Course> course = courseJpaRepository.findById(1L);
        List<Lesson> lessons = lessonJpaRepository.findAllByCourse(course.get());
        System.out.println(lessons);

    }


    @Test
    public void init() {
        courseJpaRepository.findAll();
    }

    @Test
    public void fillBase() {

        teacher.setName("Yulia");
        teacher.setSurname("Ivanauskas");
        teacher.setLogin("Yulia");
        teacher.setPassword(PASSWORD.toCharArray());
        teacher.setEmail("Yulia@gmail.com");
        teacher.setRole(Role.TEACHER);
        teacher.setEnabled(true);
        userJpaRepository.save(teacher);

        student1.setName("Kostya");
        student1.setSurname("Piskunou");
        student1.setLogin("Kostya1308");
        student1.setPassword(PASSWORD.toCharArray());
        student1.setEmail("kostya1308@gmail.com");
        student1.setRole(Role.STUDENT);
        student1.setEnabled(true);
        userJpaRepository.save(student1);

        student2.setName("Alex");
        student2.setSurname("Ivanov");
        student2.setLogin("Alex");
        student2.setPassword(PASSWORD.toCharArray());
        student2.setEmail("alex@gmail.com");
        student2.setRole(Role.STUDENT);
        student2.setEnabled(true);
        userJpaRepository.save(student2);

        javaCoreCourse.setTitle("Java Core");
        javaCoreCourse.setDateStart(LocalDate.of(2023, 6, 6));
        javaCoreCourse.setDateEnd(LocalDate.of(2023, 8, 6));
        javaCoreCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
        javaEnterpriseCourse.setTitle("Java Enterprise");
        javaEnterpriseCourse.setDateStart(LocalDate.of(2023, 6, 6));
        javaEnterpriseCourse.setDateEnd(LocalDate.of(2023, 8, 6));
        javaEnterpriseCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
        dataBasecourse.setTitle("Database");
        dataBasecourse.setDateStart(LocalDate.of(2023, 6, 6));
        dataBasecourse.setDateEnd(LocalDate.of(2023, 8, 6));
        dataBasecourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");
        javaScriptCourse.setTitle("JavaScript");
        javaScriptCourse.setDateStart(LocalDate.of(2023, 6, 6));
        javaScriptCourse.setDateEnd(LocalDate.of(2023, 8, 6));
        javaScriptCourse.setDescription("Java Core is a term that can be used differently in different contexts. If it appears in job postings for a junior-level developer, it usually refers to a basic knowledge of Java. But Sun Microsystems, where the Java language was developed, defines Core Java as a Java-based computing platform.");

        courseJpaRepository.save(javaCoreCourse);
        courseJpaRepository.save(javaEnterpriseCourse);
        courseJpaRepository.save(dataBasecourse);
        courseJpaRepository.save(javaScriptCourse);

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

        lesson2.setCourse(javaCoreCourse);
        lesson2.setStudents(students);
        lesson2.setTeachers(teachers);
        lessonJpaRepository.save(lesson2);

        lesson3.setCourse(javaCoreCourse);
        lesson3.setStudents(students);
        lesson3.setTeachers(teachers);
        lessonJpaRepository.save(lesson3);
    }

//    @AfterAll
//    public void clearBase() {
//        lessonJpaRepository.deleteAll();
//        homeworkJpaRepository.deleteAll();
//        courseJpaRepository.deleteAll();
//        userJpaRepository.deleteAll();
//    }
}