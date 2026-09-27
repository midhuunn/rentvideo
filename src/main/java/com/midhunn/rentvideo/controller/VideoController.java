package com.midhunn.rentvideo.controller;

import com.midhunn.rentvideo.dto.VideoRequest;
import com.midhunn.rentvideo.models.Video;
import com.midhunn.rentvideo.service.VideoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    // CUSTOMER and ADMIN
    @GetMapping("/videos/available")
    public List<Video> available() {
        return service.available();
    }

    // ADMIN only
    @PostMapping("/admin/videos")
    public ResponseEntity<Video> create(
            @Valid @RequestBody VideoRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    // ADMIN only
    @PutMapping("/admin/videos/{id}")
    public Video update(
            @PathVariable Long id,
            @Valid @RequestBody VideoRequest request) {

        return service.update(id, request);
    }

    // ADMIN only
    @DeleteMapping("/admin/videos/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}