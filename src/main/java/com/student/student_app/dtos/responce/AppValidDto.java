package com.student.student_app.dtos.responce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppValidDto {
    private Timestamp timestamp;
    private Integer status;
    private String code;
    private List<Map<String, String>> message;
    private String developerMessage;
    private String path;

    public AppValidDto(List<Map<String, String>> message, WebRequest webRequest, HttpStatus httpStatus, String developerMessage) {
        this(message, ((ServletWebRequest) webRequest).getRequest().getRequestURI(), httpStatus, developerMessage);
    }

    public AppValidDto(List<Map<String, String>> message, String path, HttpStatus httpStatus, String developerMessage) {
        this.developerMessage = developerMessage;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }


    @Builder
    public AppValidDto(HttpStatus status, List<Map<String, String>> message, String developerMessage, String path) {
        this.developerMessage = developerMessage;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.code = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

}
