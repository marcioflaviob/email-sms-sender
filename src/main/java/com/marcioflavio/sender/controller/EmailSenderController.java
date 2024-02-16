package com.marcioflavio.sender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcioflavio.sender.core.exceptions.EmailServiceException;
import com.marcioflavio.sender.entity.EmailSmsEntity;
import com.marcioflavio.sender.service.EmailSmsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/send")
public class EmailSenderController {

    private final EmailSmsService emailSmsService;
    
    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailSmsEntity request) {
        try {
            this.emailSmsService.sendEmail(request);
            return ResponseEntity.ok("Email sent succesfully");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }
    
}
