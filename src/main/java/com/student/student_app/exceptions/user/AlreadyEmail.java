package com.student.student_app.exceptions.user;

import com.student.student_app.exceptions.ValidationException;

public class AlreadyEmail extends ValidationException {
    public AlreadyEmail(String message) {
        super(message);
    }

    public AlreadyEmail(String message, Throwable cause) {
        super(message, cause);
    }
}
