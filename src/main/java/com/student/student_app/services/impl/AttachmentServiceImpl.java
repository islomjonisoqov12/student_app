package com.student.student_app.services.impl;

import com.student.student_app.exceptions.NotFoundException;
import com.student.student_app.models.Attachment;
import com.student.student_app.repositories.AttachmentRepository;
import com.student.student_app.services.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;
    private String url = ".\\src\\main\\resources\\files";


    @Override
    public Attachment saveFiles(MultipartFile file) {
        File dir = new File(url);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        Attachment attachment = makeAttachmentFromFile(file);
        repository.save(attachment);
        try {
            File newFile = new File(dir.getAbsolutePath()+"\\"+attachment.getId()+attachment.getOriginalName());
            attachment.setPath(url+"\\"+attachment.getId()+attachment.getOriginalName());
            file.transferTo(newFile);
            repository.save(attachment);
        }catch (Exception e){
            repository.deleteById(attachment.getId());
            e.printStackTrace();
        }

        return attachment;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String id) throws FileNotFoundException {
        Attachment attachment = repository.findById(id).orElseThrow(() -> new NotFoundException("file not found by id"));
        File file = new File(attachment.getPath());
        InputStream inputStream = new FileInputStream(file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=" + attachment.getOriginalName())
                .body(new InputStreamResource(inputStream));
    }

    @Override
    public void deleteAttachments(String... ids) {
        File dir = new File(url);
        for (String id : ids) {
            Optional<Attachment> byId= repository.findById(id);
            byId.ifPresent(
                    a -> {
                        File file = new File(a.getPath());
                        if (file.exists()) {
                            file.delete();
                        }
                        repository.deleteById(a.getId());
                    }
            );
        }
    }

    public Attachment makeAttachmentFromFile(MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());
        attachment.setName(file.getName());
        attachment.setOriginalName(file.getOriginalFilename());
        return attachment;
    }
}
