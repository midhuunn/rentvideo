package com.midhunn.rentvideo.controller;

import com.midhunn.rentvideo.dto.RentalResponse;
import com.midhunn.rentvideo.service.RentalService;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<RentalResponse> rent(
            @PathVariable Long videoId,
            Principal principal) {

        RentalResponse response = service.rent(
                principal.getName(),
                videoId
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{rentalId}/return")
    public RentalResponse returnVideo(
            @PathVariable Long rentalId,
            Principal principal) {

        return service.returnVideo(
                principal.getName(),
                rentalId
        );
    }

    @GetMapping("/active")
    public List<RentalResponse> active(
            Principal principal) {

        return service.active(
                principal.getName()
        );
    }
}