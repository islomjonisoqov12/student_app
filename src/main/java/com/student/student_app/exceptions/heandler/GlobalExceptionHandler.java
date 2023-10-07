package com.student.student_app.exceptions.heandler;


import com.student.student_app.dtos.responce.AppErrorDto;
import com.student.student_app.dtos.responce.AppValidDto;
import com.student.student_app.dtos.responce.DataDto;
import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.exceptions.user.UserBlockedException;
import com.student.student_app.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ControllerAdvice("com.student.student_app")
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Map<String, String>> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", error.getField());
            errorMap.put("message", error.getDefaultMessage());
            fieldErrors.add(errorMap);
        });
        return new ResponseEntity<>(
                DataDto.<AppValidDto>builder()
                        .success(false)
                        .appValidDto(
                                new AppValidDto(
                                        fieldErrors,
                                        request,
                                        HttpStatus.resolve(status.value()),
                                        "validation exception"
                                )
                        ).build(), HttpStatus.BAD_REQUEST
        );
    }
}
