package edu.montgomerycollege.drdoom.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ws.mime.Attachment;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String from, String to, String title, String body, File file) {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            if (from != null) {
                mimeMessageHelper.setFrom(from);
            }
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.addAttachment("testFile.txt", new ClassPathResource("C:\\Users\\Jesse\\IdeaProjects\\RobotInterview\\testFile.txt"));
            mimeMessageHelper.addAttachment("testFile.txt", file);
            //message.addAttachment("testFile.txt", new ClassPathResource("testFile.txt"));
            this.javaMailSender.send(message);
        } catch (MessagingException messageException) {
            // You could also 'throw' this exception. I am not a fan of checked exceptions.
            // If you want to go that route, then just update this method and the interface.
            System.out.println("messageException");
            throw new RuntimeException(messageException);
        }
    }




}
