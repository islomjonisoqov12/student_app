package com.student.student_app.exceptions.user;

import com.student.student_app.exceptions.ValidationException;

public class AlreadyUsername extends ValidationException {
    public AlreadyUsername(String message) {
        super(message);
    }

    public AlreadyUsername(String message, Throwable cause) {
        super(message, cause);
    }
}
