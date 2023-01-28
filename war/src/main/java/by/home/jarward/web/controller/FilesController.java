package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.User;
import by.home.jarward.web.service.intarfaces.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/file")

public class FilesController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/show-photo")
    public ResponseEntity<byte[]> showPhoto(@RequestParam("param") String value, HttpServletRequest req) throws IOException {
        ServletContext servletContext = req.getServletContext();
        String resource;
        switch (value) {
            case "1" -> resource = "static/images/student.jpg";
            default -> resource = "static/images/student.jpg";
        }
        InputStream inputStream = servletContext.getResourceAsStream(resource);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();

        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @GetMapping(value = "/show-avatar")
    public void showAvatar(@RequestParam(value = "login", defaultValue = "myself") String login, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (Objects.equals(login, "myself")) {
            Object photo = req.getSession().getAttribute("photo");
            resp.setContentType("image/jpg");
            resp.getOutputStream().write((byte[]) photo);
        } else {
            Optional<User> user = userService.getByLogin(login);
            user.ifPresent(itemUser -> {
                byte[] photo = itemUser.getPhoto();
                resp.setContentType("image/jpg");
                try {
                    resp.getOutputStream().write(photo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            resp.getOutputStream().close();

        }

    }


}
