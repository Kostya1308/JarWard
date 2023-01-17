package by.home.jarward.web.form;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Getter
@Setter
public class UserForm {

    private String name;
    private String surname;
    private String login;
    private String password;
    @Email(message = "incorrect format")
    private String email;
    private String dateOfBirth;
    private String gender;
    private String language;
    private MultipartFile fileData;
    private byte[] photo;
    private String role;
    private String isRemember;
}
