package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Access(AccessType.FIELD)
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
    private LocalDateTime dateTimeStart;
    @Column
    private LocalDateTime dateTimeEnd;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_student",
            joinColumns = {@JoinColumn(name = "idLesson")},
            inverseJoinColumns = {@JoinColumn(name = "idUser")})
    @ToString.Exclude
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
