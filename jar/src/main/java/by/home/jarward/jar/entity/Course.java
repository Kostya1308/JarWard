package by.home.jarward.jar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Access(AccessType.FIELD)
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Course extends DateTimeEntity implements Serializable {
    @Version
    private static long serialVersionUID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCourse")

    private Long id;
    @Column
    private String title;
    @Column
    @Lob
    private String description;
    @Column
    private LocalDate dateStart;
    @Column
    private LocalDate dateEnd;
    @Column
    private boolean isClosed;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = {@JoinColumn(name = "idCourse")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    @ToString.Exclude
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_teacher",
            joinColumns = {@JoinColumn(name = "idCourse")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    @ToString.Exclude

    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @ToString.Exclude

    private List<Lesson> lessons;
    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @ToString.Exclude

    private List<Homework> homeworks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }


}
