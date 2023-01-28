package by.home.jarward.web.facade;

import by.home.jarward.jar.entity.User;
import by.home.jarward.jar.enums.Gender;
import by.home.jarward.web.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class UserFacade {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserFromUserForm(User user, UserForm userForm) throws IOException {
        String login = userForm.getLogin();
        String name = userForm.getName();
        char[] passwordField = new JPasswordField(userForm.getPassword()).getPassword();
        String surname = userForm.getSurname();
        String email = userForm.getEmail();
        String language = userForm.getLanguage();
        String role = userForm.getRole();
        String dateOfBirth = userForm.getDateOfBirth();
        String gender = userForm.getGender();
        byte[] bytes = userForm.getFileData().getBytes();

        if (login != null){
            user.setLogin(login);
        }
        if (passwordField.length >= 1){
            user.setPassword(passwordEncoder.encode(String.copyValueOf(passwordField)).toCharArray());
        }
        if (name != null){
            user.setName(name);
        }
        if (surname != null){
            user.setSurname(surname);
        }
        if (email != null){
            user.setEmail(email);
        }
        if (language !=null){
            user.setLanguage(language);
        }
        if (dateOfBirth != null) {
            user.setDateOfBirth(LocalDate.parse(dateOfBirth));
        }
        if (gender != null){
            switch (gender) {
                case "Male" -> user.setGender(Gender.MALE);
                case "Female" -> user.setGender(Gender.FEMALE);
                case "Other" -> user.setGender(Gender.OTHER);
            }
        }

        if (bytes.length >= 1){
            user.setPhoto(bytes);
        }
        passwordField = new char[]{0, 0, 0, 0};

        return user;
    }

    public UserForm getUserFormFromUser(UserForm userForm, User user){
        String login = user.getLogin();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String language = user.getLanguage();
        String role = user.getRole().getName();
        LocalDate dateOfBirth = user.getDateOfBirth();
        Gender gender = user.getGender();
        byte[] photo = user.getPhoto();

        userForm.setLogin(login);
        userForm.setName(name);
        userForm.setSurname(surname);
        userForm.setEmail(email);
        userForm.setLanguage(language);
        userForm.setDateOfBirth(String.valueOf(dateOfBirth));
        userForm.setGender(gender.getName());
        userForm.setPhoto(photo);

        return userForm;
    }



}
