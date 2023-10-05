package com.student.student_app.exceptions.heandler;


import com.student.student_app.dtos.responce.AppErrorDto;
import com.student.student_app.dtos.responce.DataDto;
import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.exceptions.user.UserBlockedException;
import com.student.student_app.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice("com.student.student_app")
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>
                                builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.METHOD_NOT_ALLOWED,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {UserBlockedException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerUserBlockedException(UserNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>
                                builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.BAD_REQUEST,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerOtherCustomExceptions(NotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>
                                builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.BAD_REQUEST,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.NOT_FOUND);
    }

}
