package com.marcioflavio.sender.infra.ses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.marcioflavio.sender.adapters.EmailSenderGateway;
import com.marcioflavio.sender.core.exceptions.EmailServiceException;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Value("${spark.post.api.key}")
    private String sparkPostKey;
    
    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
            .withSource("oi@marcioflavio.com") //Email from
            .withDestination(new Destination().withToAddresses(to))
            .withMessage(new Message()
                .withSubject(new Content(subject))
                .withBody(new Body(new Content(body)))
            );
        try {
            this.amazonSimpleEmailService.sendEmail(request);
       } catch (AmazonServiceException exception) {
            try {
                Client client = new Client(sparkPostKey); // If AWS fails it tries to send through SparkPost.
                client.sendMessage("oi@marcioflavio.com", to, subject, "", "<p>" + body + "</p>");
            } catch (SparkPostException sPostException) {
                throw new EmailServiceException("Both AWS and SparkPost failed sending this email.", sPostException);
            }
        }
    }
    
}
