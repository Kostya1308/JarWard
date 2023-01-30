package by.home.jarward.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

    @GetMapping
    public void getAbout(){
        throw new RuntimeException("Exception has been caught");
    }
}
