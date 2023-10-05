package com.student.student_app.exceptions.user;


import com.student.student_app.exceptions.ValidationException;

public class UserNotFoundException extends ValidationException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
