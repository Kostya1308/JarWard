package by.home.jarward.jar.entity;

import by.home.jarward.jar.enums.Gender;
import by.home.jarward.jar.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("null")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends DateTimeEntity implements Serializable {
    @Version
    private static long serialVersionUID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String login;
    @Lob
    private char[] password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column
    private LocalDate dateOfBirth;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String language;
    @Column(length = Integer.MAX_VALUE)
    @Lob
    private byte[] photo;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private boolean isBlocked;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_course",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idCourse")})
    @ToString.Exclude
    private List<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
