package com.student.student_app.services;

public interface EmailSender {
    void sendEmailVerification(String to_email, String verify_code);
}
