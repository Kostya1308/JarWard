package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.Student;
import by.home.jarward.jar.entity.User;
import by.home.jarward.jar.enums.Role;
import by.home.jarward.web.facade.UserFacade;
import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.EmailService;
import by.home.jarward.web.service.intarfaces.UserService;
import by.home.jarward.web.validator.NewUserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import java.util.Optional;
import java.util.Properties;

@Controller
@RequestMapping(value = "/sign-up")
@PropertySource("classpath:mail_sender.properties")
public class SignUpController {
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserFacade userFacade;

    @Qualifier(value = "newUserValidator")
    @Autowired
    NewUserValidator newUserValidator;

    @Autowired
    Properties properties;

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
            userFacade.getUserFromUserForm(user, userForm);
            user.setBlocked(false);
            user.setRole(Role.STUDENT);
            user.setEnabled(false);
            user.setVerifyToken(passwordEncoder.encode(userForm.getLogin()).toCharArray());
            emailService.sendEmail("konstantinpiskunov1308@gmail.com", properties.getProperty("admin.email"),
                    "Verify your account",
                    "http://localhost:8080/sign-up/verify?login=" + user.getLogin() + "&token="
                            + String.copyValueOf(user.getVerifyToken()));
            userService.save(user);

            return new RedirectView(req.getContextPath() + "/sign-up/verify-info");
        }
    }

    @GetMapping(value = "/verify-info")
    public ModelAndView verify() {
        return new ModelAndView("verify");
    }

    @GetMapping(value = "/verify")
    public RedirectView signUpAfterVerify(@RequestParam("login") String login, @RequestParam("token") String token) {
        Optional<User> user = userService.getByLogin(login);
        if (user.isPresent() && String.copyValueOf(user.get().getVerifyToken()).equals(token)) {
            user.get().setEnabled(true);
            userService.save(user.get());

            return new RedirectView("/");
        } else {
            return new RedirectView("/error");
        }
    }
}
