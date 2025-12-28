package com.smanagement.controller;

import com.smanagement.dto.SchoolDto;
import com.smanagement.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Tag(name = "Schools", description = "Operations about schools")
@RestController
@RequestMapping("/api/schools")
@Slf4j
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @ModelAttribute SchoolDto wrapper,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        log.info("Creating school (JSON) with data: {}", wrapper);
        SchoolDto created = schoolService.create(wrapper);
        return ResponseEntity.created(URI.create("/api/schools/" + created.getSchoolId())).body(created);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a school by id")

    public ResponseEntity<SchoolDto> getById(@PathVariable String id) {
        SchoolDto dto = schoolService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    /*@GetMapping
    @Operation(summary = "Get all schools")
    public ResponseEntity<List<SchoolDto>> getAll() {
        return ResponseEntity.ok(schoolService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a school")
    public ResponseEntity<SchoolDto> update(@PathVariable Long id, @Valid @RequestBody SchoolDto dto) {
        SchoolDto updated = schoolService.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a school")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        schoolService.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}
