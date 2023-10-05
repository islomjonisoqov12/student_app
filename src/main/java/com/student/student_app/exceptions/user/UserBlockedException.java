package com.student.student_app.exceptions.user;


import com.student.student_app.exceptions.ValidationException;

public class UserBlockedException extends ValidationException {
    public UserBlockedException(String message) {
        super(message);
    }

    public UserBlockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
