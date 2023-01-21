package by.home.jarward.web.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/file")

public class FilesController {

    @GetMapping(value = "/show-photo")
    public ResponseEntity<byte[]> showPhoto(@RequestParam("param") String value, HttpServletResponse resp,
                                            HttpServletRequest req) throws IOException {
        ServletContext servletContext = req.getServletContext();
        String resource;
        switch (value){
            case "1" -> resource = "static/images/student.jpg";
            default -> resource = "static/images/student.jpg";
        }
        InputStream inputStream = servletContext.getResourceAsStream(resource);
        byte[] bytes = inputStream.readAllBytes();
        return new ResponseEntity<>(bytes, HttpStatus.OK);

    }
}
