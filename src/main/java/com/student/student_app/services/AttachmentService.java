package com.student.student_app.services;

import com.student.student_app.models.Attachment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface AttachmentService {

    Attachment saveFiles(MultipartFile file);

    ResponseEntity<InputStreamResource> downloadFile(String id) throws FileNotFoundException;

    void deleteAttachments(String... ids);
}
