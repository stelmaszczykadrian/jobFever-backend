package com.jobfever.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendingEmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendRecoveryMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        String messageHeader = "Link to password recovery: ";
        message.setFrom("job.fever.contact@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageHeader + text);
        emailSender.send(message);
    }
    public void sendContactMessage(String name, String email, String phoneNumber, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("job.fever.contact@gmail.com");
        simpleMailMessage.setTo("job.fever.contact@gmail.com");
        simpleMailMessage.setSubject("Name: " + name + " Email: " + email);
        simpleMailMessage.setText("Name: " + name +
                "\nEmail: " + email +
                "\nPhone number: " + phoneNumber +
                "\n" + message);
        emailSender.send(simpleMailMessage);
    }
}
