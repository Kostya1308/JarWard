package by.home.jarward.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
        e.printStackTrace();
        return new ModelAndView("error");
    }
}
