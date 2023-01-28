package by.home.jarward.web.form;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
public class HomeworkForm {
    private String login;
    private String studentName;
    @Pattern(regexp = "^([1-9]|1[0])$", message = "the mark can be from 1 to 10")
    private String mark;

}
