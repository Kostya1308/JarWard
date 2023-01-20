package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Student;
import by.home.jarward.jar.entity.User;
import by.home.jarward.jar.enums.Gender;
import by.home.jarward.jar.enums.Role;
import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.UserService;
import by.home.jarward.web.validator.NewUserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.*;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/sign-up")
public class SignUpController {
    @Autowired
    UserService userService;

    @Qualifier(value = "newUserValidator")
    @Autowired
    NewUserValidator newUserValidator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(newUserValidator);
    }

    @GetMapping
    public ModelAndView getSignUpPage(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        return new ModelAndView("sign_up");
    }

    @PostMapping
    public RedirectView signUp(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpServletRequest req) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm",
                    bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);

            return new RedirectView(req.getContextPath() + "/sign-up");
        } else {
            Student user = new Student();
            user.setLogin(userForm.getLogin());
            char[] passwordField = new JPasswordField(userForm.getPassword()).getPassword();
            user.setPassword(passwordEncoder.encode(String.copyValueOf(passwordField)).toCharArray());
            user.setEmail(userForm.getEmail());
            user.setName(userForm.getName());
            user.setSurname(userForm.getSurname());
            user.setDateOfBirth(LocalDate.parse(userForm.getDateOfBirth()));
            switch (userForm.getGender()){
                case "Male" -> user.setGender(Gender.MALE);
                case "Female" -> user.setGender(Gender.FEMALE);
                case "Other" -> user.setGender(Gender.OTHER);
            }
            user.setBlocked(false);
            user.setRole(Role.STUDENT);
            user.setPhoto(userForm.getFileData().getBytes());

            userService.save(user);

            return new RedirectView(req.getContextPath());
        }
    }









}
