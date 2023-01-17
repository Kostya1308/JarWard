package by.home.jarward.jar.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class MarkId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idHomework")
    private Homework homework;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Student student;




}
