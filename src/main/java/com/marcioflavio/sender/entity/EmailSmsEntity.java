package com.marcioflavio.sender.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSmsEntity {

    String phone_number;

    String subject;

    String email;

    String message;
    
}
