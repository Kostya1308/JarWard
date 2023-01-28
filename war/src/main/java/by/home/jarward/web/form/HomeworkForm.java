package by.home.jarward.web.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
public class HomeworkForm {

    private String studentName;
    private String mark;

}
