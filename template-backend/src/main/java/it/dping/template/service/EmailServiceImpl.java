package it.dping.template.service;

import it.dping.template.config.StorageProperties;
import it.dping.template.model.EmailDetails;
import it.dping.template.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@Service
public class EmailServiceImpl implements EmailService {

    private final Path rootLocation;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired private JavaMailSender javaMailSender;



    public EmailServiceImpl(@Autowired JavaMailSender javaMailSender, StorageProperties properties) {
        this.javaMailSender = javaMailSender;
        this.rootLocation =  Paths.get(properties.getLocation());
    }


    @Async
    public void registrationUser(User user) {

        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Registrazione utente");
            mailMessage.setText("" +
                    "Template: registrazione avvenuta con successo.");
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
              }
    }

    @Async
    public void recoveryPassword(User user) {

        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Registrazione utente");
            mailMessage.setText("Procedura per password dimentica. \n\n"+

                    "Descrizione: Link cambio Password https://www.template.it/password");
            javaMailSender.send(mailMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public EmailDetails contactUser(EmailDetails details) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        Date today = calendar.getTime();
        String htmlMsg = "<!DOCTYPE html>\n"; //usare il template stream da file

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessage.setContent(htmlMsg, "text/html; charset=utf-8");
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());

            javaMailSender.send(mimeMessage);
            details.setMsgBody(mimeMessage.getContent().toString());
            return details;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Async
    public String sendMailWithAttachment(EmailDetails details) {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());
            mimeMessage.setContent(mimeMessageHelper, "text/html; charset=utf-8");

            Path destinationFileTest = this.rootLocation.resolve("path").normalize(); //generare un path valido
            destinationFileTest = destinationFileTest.resolve(details.getAttachment());
            System.err.println(destinationFileTest);

            FileSystemResource file
                    = new FileSystemResource(
                    new File(destinationFileTest.toString()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
}
