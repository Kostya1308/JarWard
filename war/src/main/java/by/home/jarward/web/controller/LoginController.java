package by.home.jarward.web.controller;

import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public ModelAndView getValidateLoginPage(@ModelAttribute("userForm") UserForm userForm,
                                             @RequestParam(value = "error", required = false) String error, HttpServletRequest req,
                                             HttpServletResponse resp) throws IOException {
        ModelAndView modelAndView = new ModelAndView("login");
        HttpSession session = req.getSession();

        if (error != null) {
            session.setAttribute("isValid", "false");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

        return modelAndView;
    }

    @GetMapping
    public ModelAndView home(){
        return new ModelAndView("home");
    }

}
