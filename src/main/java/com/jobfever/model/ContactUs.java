package com.jobfever.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactUs {
    String name;
    String email;
    String phoneNumber;
    String message;
}
