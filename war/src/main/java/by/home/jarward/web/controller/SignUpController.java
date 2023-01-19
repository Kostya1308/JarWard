package by.home.jarward.web.controller;

import by.home.jarward.web.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/sign-up")
public class SignUpController {

    @GetMapping
    public ModelAndView getSignUpPage() {
        UserForm userForm = new UserForm();
        ModelAndView modelAndView = new ModelAndView("sign_up");
        modelAndView.addObject("userForm", userForm);
        return modelAndView;
    }



}
