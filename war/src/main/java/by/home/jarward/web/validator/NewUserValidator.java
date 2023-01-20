package by.home.jarward.web.validator;


import by.home.jarward.jar.entity.User;
import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.swing.*;
import java.util.Arrays;
import java.util.Optional;

@Component(value = "newUserValidator")
public class NewUserValidator implements Validator {
    @Autowired
    Validator validator;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target, errors);

        UserForm userForm = (UserForm) target;
        char[] passwordField = new JPasswordField(userForm.getPassword()).getPassword();
        char[] passwordConfirmField = new JPasswordField(userForm.getPasswordConfirm()).getPassword();

        if (!Arrays.equals(passwordField, passwordConfirmField)) {
            errors.rejectValue("passwordConfirm", "", "password is not confirm");
        }

        Optional<User> userByLogin = userService.getByLogin(userForm.getLogin());
        if (userByLogin.isPresent() && userByLogin.get().getLogin().equals(userForm.getLogin())) {
            errors.rejectValue("login", "", "login \"" + userForm.getLogin() + "\" is occupied");
        }

        Optional<User> userByEmail = userService.getByEmail(userForm.getEmail());
        if (userByEmail.isPresent() && userByEmail.get().getEmail().equals(userForm.getEmail())) {
            errors.rejectValue("email", "", "e-mail \"" + userForm.getEmail() + "\" is occupied");
        }
    }
}
