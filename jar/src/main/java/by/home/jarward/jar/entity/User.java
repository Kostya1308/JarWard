package by.home.jarward.jar.entity;

import by.home.jarward.jar.enums.Gender;
import by.home.jarward.jar.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("null")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Access(AccessType.FIELD)
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
    @Column
    private boolean enabled;
    @Column
    private char[] verifyToken;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public char[] getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(char[] verifyToken) {
        this.verifyToken = verifyToken;
    }
}


