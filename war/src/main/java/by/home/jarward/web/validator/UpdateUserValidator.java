package by.home.jarward.web.validator;

import by.home.jarward.jar.entity.User;
import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.swing.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component("updateUserValidator")
public class UpdateUserValidator implements Validator {
    @Autowired
    Validator validator;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oldUser = userService.getByLogin(principal.getUsername());
        AtomicReference<String> oldLogin = new AtomicReference<>();
        AtomicReference<String> oldEmail = new AtomicReference<>();
        oldUser.ifPresent(item -> {
            oldLogin.set(item.getLogin());
            oldEmail.set(item.getEmail());

        });

        validator.validate(target, errors);

        UserForm userForm = (UserForm) target;
        char[] passwordField = new JPasswordField(userForm.getPassword()).getPassword();
        char[] passwordConfirmField = new JPasswordField(userForm.getPasswordConfirm()).getPassword();

        if (passwordField.length >= 1 && oldUser.isPresent() &&
                !String.copyValueOf(oldUser.get().getPassword()).equals(passwordEncoder.encode(String.copyValueOf(passwordField)))) {
            errors.rejectValue("password", "", "password is not confirm");
        }

        Optional<User> newUserByLogin = userService.getByLogin(userForm.getLogin());
        if (newUserByLogin.isPresent() && !userForm.getLogin().equals(oldLogin.get())) {
            errors.rejectValue("login", "", "login \"" + userForm.getLogin() + "\" is occupied");
        }

        Optional<User> newUserByEmail = userService.getByEmail(userForm.getEmail());
        if (newUserByEmail.isPresent() && !userForm.getEmail().equals(oldEmail.get())) {
            errors.rejectValue("email", "", "e-mail \"" + userForm.getEmail() + "\" is occupied");
        }
    }
}
