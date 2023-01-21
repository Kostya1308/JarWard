package by.home.jarward.web.service.intarfaces;

public interface EmailService {

    void sendEmail(String toAddress, String fromAddress, String subject, String message);

}
