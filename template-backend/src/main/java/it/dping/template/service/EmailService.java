package it.dping.template.service;

import it.dping.template.model.EmailDetails;
import it.dping.template.model.User;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void registrationUser(User user);

    @Async
    void recoveryPassword(User user);

    @Async
    EmailDetails contactUser(EmailDetails details);
}

