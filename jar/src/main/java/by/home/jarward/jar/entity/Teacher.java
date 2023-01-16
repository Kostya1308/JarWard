package by.home.jarward.jar.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.Year;
import java.util.List;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {
    @Column
    @Lob
    private String profile;
    @Column
    private String company;
    @Column
    private Year sinceWorkExperience;
    @Column
    private Year sinceTeachingExperience;
    @Column
    private String technologyStack;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Lesson> lessons;

}
