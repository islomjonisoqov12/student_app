package com.student.student_app.services.impl;

import com.student.student_app.services.EmailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Service
public class EmailSenderImpl implements EmailSender {

    @Override
    public void sendEmailVerification(String toEmail, String verifyCode) {
        final String username = "axranaeva@gmail.com";
        final String password = "kwql oqyj sbyp rddm";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Your Verification Code");

            // Construct the HTML body
            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 10px; background-color: #f4f4f4;'>"
                    + "<h2 style='color: #2a2a2a;'>Welcome to Our Service!</h2>"
                    + "<p>Thank you for registering. Here's your verification code:</p>"
                    + "<div style='font-size: 24px; color: #e74c3c; font-weight: bold;'>"
                    + verifyCode
                    + "</div>"
                    + "<p>Enter this code in our application to complete the verification process.</p>"
                    + "</div>";

            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

