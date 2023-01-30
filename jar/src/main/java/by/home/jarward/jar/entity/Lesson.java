package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Access(AccessType.FIELD)
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Lesson extends DateTimeEntity implements Serializable {
    @Version
    private static long serialVersionUID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLesson")
    private Long id;

    @Column
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCourse")
    @ToString.Exclude
    private Course course;

    @Column
    private LocalDate dateStart;

    @Column
    private LocalDate dateEnd;

    @Column
    private LocalTime timeStart;

    @Column
    private LocalTime timeEnd;

    @Column
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_student",
            joinColumns = {@JoinColumn(name = "idLesson")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    @ToString.Exclude
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_teacher",
            joinColumns = {@JoinColumn(name = "idLesson")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    @ToString.Exclude
    private List<Teacher> teachers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lesson lesson = (Lesson) o;
        return id.equals(lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
