package com.student.student_app.controllers;

import com.student.student_app.models.Attachment;
import com.student.student_app.services.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
@Tag(name = "Attachment Controller", description = "apis for authentication")
public class AttachmentController {

    private final Logger log = LoggerFactory.getLogger(AttachmentController.class);
    private final AttachmentService service;

    /**
     * Save one file for student lesson file api
     * @param file
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize(value = "isAuthenticated()")
    @Operation(summary = "save file api for lesson file", description = "Save one file for student's lesson file api", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Attachment> saveUser(@RequestPart MultipartFile file){
        log.debug("file info: {}", file.getOriginalFilename());
        Attachment attachment = service.saveFiles(file);
        return ResponseEntity.ok(attachment);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String id) throws IOException {
        return service.downloadFile(id);
    }

    @DeleteMapping
    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    public void delete(@RequestParam String id){
        service.deleteAttachments(id);
    }
}
