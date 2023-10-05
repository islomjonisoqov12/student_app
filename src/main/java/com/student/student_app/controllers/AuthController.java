package com.student.student_app.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.student.student_app.config.Constants;
import com.student.student_app.dtos.auth.LoginVM;
import com.student.student_app.dtos.auth.RegisterUserDTO;
import com.student.student_app.dtos.auth.VerifyEmailDto;
import com.student.student_app.dtos.student.StudentDto;
import com.student.student_app.repositories.StudentRepository;
import com.student.student_app.security.jwt.JWTFilter;
import com.student.student_app.security.jwt.JwtProvider;
import com.student.student_app.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "apis for authentication")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    /**
     * Login student
     * @param loginVM body params
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "Sing-in for student page", description = "This method of request of authentication user")
    public ResponseEntity<JWTToken> signInMManager(@Valid @RequestBody LoginVM loginVM) {
        log.debug("REST request to create user by admin page by the login: {}", loginVM.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * verify email when registration
     * @param dto email, code
     * @return
     */

    @PostMapping("/verify-email")
    @Operation(summary = "verify email when registration", description = "This method of request of verified")
    public ResponseEntity<Boolean> verifyEmail(@Valid @RequestBody VerifyEmailDto dto) {
        log.debug("REST request to verify email dto: {}", dto.toString());
        Boolean verified = studentService.verifyEmail(dto);
        return ResponseEntity.ok(verified);
    }
    @GetMapping("/send-email")
    @Operation(summary = "send code when registration", description = "This method send verification code to email")
    public ResponseEntity<Void> sendVerifyCodeEmail(@RequestParam String email){
        log.debug("email: {}", email);
        studentService.sendCode(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registration")
    @Operation(summary = "send code when registration", description = "This method send verification code to email")
    public ResponseEntity<StudentDto> saveUser(@Valid @RequestBody RegisterUserDTO dto){
        log.debug("user info: {}", dto.toString());
        StudentDto studentDto = studentService.saveUser(dto);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping("/me")
    @PreAuthorize(value = "isAuthenticated()")
    @Operation(summary = "get currect authenticated user", description = "This method will give current authenticated user")
    public ResponseEntity<StudentDto> getCurrentUser(){
        log.debug("user account me");
        StudentDto studentDto = studentService.getCurrentUser();
        return ResponseEntity.ok(studentDto);
    }



    /**
     * class for token
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {return idToken;}

        void setIdToken(String idToken) { this.idToken = idToken; }
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
                (StringUtils.isEmpty(password)) ||
                        password.length() < Constants.PASSWORD_MIN_LENGTH ||
                        password.length() > Constants.PASSWORD_MAX_LENGTH
        );
    }

}
