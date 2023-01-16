package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@DiscriminatorValue("student")
public class Student extends User {
    @Column
    private String education;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Homework> homeworks;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Lesson> lessons;

}
