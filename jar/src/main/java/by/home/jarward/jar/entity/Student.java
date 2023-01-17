package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@DiscriminatorValue("student")
public class Student extends User {
    @Column
    private String education;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Course> courses;

    @OneToMany(mappedBy = "markId.homework", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mark> marks;

}
