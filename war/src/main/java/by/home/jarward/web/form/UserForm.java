package by.home.jarward.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Component
@Getter
@Setter
@Validated
public class UserForm {
    @Size(min = 2, message = "incorrect format")
    private String name;
    private String oldName;
    @Size(min = 2, message = "incorrect format")
    private String surname;
    private String oldSurname;
    @Size(min = 2, message = "incorrect format")
    private String login;
    private String oldLogin;
    @Pattern(regexp = "[1-9A-Z]{8,}", message = "incorrect format")
    private String password;
    private String passwordConfirm;
    @Email(message = "incorrect e-mail format")
    private String email;
    private String verifyCode;
    private String verifyCodeConfirm;
    private String dateOfBirth;
    private String gender;
    private String language;
    private MultipartFile fileData;
    private byte[] photo;
    private byte[] oldPhoto;
    private String role;
    private String isRemember;
}
