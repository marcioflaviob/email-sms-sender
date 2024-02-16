package com.marcioflavio.sender.service;

import org.springframework.stereotype.Service;

import com.marcioflavio.sender.core.exceptions.BlankBodyException;
import com.marcioflavio.sender.core.exceptions.UnknownDestException;
import com.marcioflavio.sender.entity.EmailSmsEntity;
import com.marcioflavio.sender.infra.ses.SesEmailSender;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailSmsService {

    SmsService smsService;
    SesEmailSender sesEmailSender;

    public void sendEmail(EmailSmsEntity emailsms) {
        String sms_message;

        if (emailsms.getSubject().isEmpty())
            emailsms.setSubject("A message for you");
        if (emailsms.getMessage().isBlank())
            throw new BlankBodyException("You have to inform a message.");
        if (emailsms.getEmail().isEmpty() && emailsms.getPhone_number().isEmpty())
            throw new UnknownDestException("You have to inform a phone number or an email address.");
        if (!emailsms.getEmail().isEmpty())
           sesEmailSender.sendEmail(emailsms.getEmail(), emailsms.getSubject(), emailsms.getMessage());
        if (!emailsms.getPhone_number().isEmpty()) {
            sms_message = emailsms.getSubject() + ": " + emailsms.getMessage();
            smsService.sendSms(emailsms.getPhone_number(), sms_message);
        }
    }
    
}
