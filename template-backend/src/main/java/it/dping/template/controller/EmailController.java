package it.dping.template.controller;

import it.dping.template.model.EmailDetails;
import it.dping.template.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")

public class EmailController {

    @Autowired private EmailService emailService;

    @PostMapping("/sendmail")
    @ResponseStatus(HttpStatus.OK)
    public EmailDetails sendMail(@RequestBody EmailDetails details) {
        return emailService.contactUser(details);
    }
}

