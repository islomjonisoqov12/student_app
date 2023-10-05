package com.student.student_app.controllers;

import com.student.student_app.criteria.NotebookCriteria;
import com.student.student_app.dtos.notebook.NotebookCDto;
import com.student.student_app.dtos.notebook.NotebookDto;
import com.student.student_app.services.NotebookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notebook")
@Tag(name = "Notebook Controller", description = "apis for notebook")
public class NotebookController {
    private final NotebookService service;

    @PostMapping
    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    public HttpEntity<NotebookDto> saveNotebook(@RequestBody @Valid NotebookCDto dto){
        NotebookDto notebookDto = service.saveNotebook(dto);
        return ResponseEntity.ok(notebookDto);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public HttpEntity<List<NotebookDto>> getNotebooks(NotebookCriteria criteria){
        List<NotebookDto> notebooks = service.getNotebooks(criteria);
        return ResponseEntity.ok(notebooks);
    }

    @PatchMapping
    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    public HttpEntity<NotebookDto> updateNotebook(@Valid @RequestBody NotebookDto dto){
        NotebookDto notebookDto = service.updateNotebook(dto);
        return ResponseEntity.ok(notebookDto);
    }

    @DeleteMapping
    @PreAuthorize(value = "isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    public void delete(@RequestParam String id){
        service.deleteNotebook(id);

    }
}
