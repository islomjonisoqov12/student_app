package com.student.student_app.exceptions.user;

import com.student.student_app.exceptions.ValidationException;

public class PhoneNumberAlready extends ValidationException{
    public PhoneNumberAlready(String message) {
        super(message);
    }

    public PhoneNumberAlready(String message, Throwable cause) {
        super(message, cause);
    }
}
