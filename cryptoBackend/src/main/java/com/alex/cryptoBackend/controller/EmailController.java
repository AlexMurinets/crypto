package com.alex.cryptoBackend.controller;

import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.service.mail.EmailServiceImpl;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailService;

    @PostMapping
    public ResponseEntity<String> sendSimpleEmail() {
        emailService.sendSimpleMessage("mardarii01@gmail.com", "Account verification", "Pupa and lupa");
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/html")
    public ResponseEntity<String> sendEmail() throws MessagingException, IOException, TemplateException {
        UserDto user = new UserDto();
        user.setEmail("mardarii01@gmail.com");
        user.setUsername("daska");
        emailService.sendEmail(user);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
