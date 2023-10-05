package com.student.student_app.exceptions.user;

import com.student.student_app.exceptions.ValidationException;

public class NotFoundUserException extends ValidationException {
    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
