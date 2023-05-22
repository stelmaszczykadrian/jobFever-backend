package com.jobfever.controller;

import com.jobfever.model.ContactUs;
import com.jobfever.service.SendingEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/contact")
@RestController
public class ContactController {
    @Autowired
    SendingEmailService sendingEmailService;
    @PostMapping("/")
    public void sendContactMessage(
            @RequestBody ContactUs contact
    ) {
        sendingEmailService.sendContactMessage(contact.getName(), contact.getEmail(), contact.getPhoneNumber(),
                contact.getMessage());
    }
}
