package by.home.jarward.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {
    @PostMapping
    public void logout(){

    }
}
