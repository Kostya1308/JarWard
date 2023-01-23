package by.home.jarward.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/course")
public class CoursesController {
    @GetMapping
    public void asd(){
        throw new RuntimeException("asasd");
    }
}
