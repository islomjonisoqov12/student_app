package com.student.student_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({
//        OpenApiProperties.class,
//        ServerProperties.class
//})
public class StudentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentAppApplication.class, args);
    }

}
