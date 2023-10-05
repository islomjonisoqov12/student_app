package com.student.student_app.exceptions.user;

import com.student.student_app.exceptions.ValidationException;

public class NotFoundUserIdException extends ValidationException {

    public NotFoundUserIdException(String message) {
        super(message);
    }

    public NotFoundUserIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
