package com.midhunn.rentvideo.service;

import com.midhunn.rentvideo.dto.RentalResponse;
import com.midhunn.rentvideo.models.*;
import com.midhunn.rentvideo.repository.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RentalService {

    private final RentalRepository rentals;
    private final VideoRepository videos;
    private final UserRepository users;

    public RentalService(
            RentalRepository rentals,
            VideoRepository videos,
            UserRepository users) {

        this.rentals = rentals;
        this.videos = videos;
        this.users = users;
    }

    @Transactional
    public RentalResponse rent(
            String email,
            Long videoId) {

        AppUser user = getUser(email);

        // Rule 1: Maximum two active rentals.
        long activeCount =
                rentals.countByUserIdAndReturnedAtIsNull(
                        user.getId()
                );

        if (activeCount >= 2) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Maximum two active rentals allowed"
            );
        }

        // Rule 2: Video must exist.
        Video video = videos.findById(videoId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Video not found"
                        )
                );

        // Rule 3: Video must be available.
        if (!video.isAvailable()
                || rentals.existsByVideoIdAndReturnedAtIsNull(
                        videoId
                )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Video is unavailable"
            );
        }

        // Mark video as unavailable.
        video.setAvailable(false);
        videos.save(video);

        // Create rental record.
        Rental rental = new Rental();

        rental.setUser(user);
        rental.setVideo(video);
        rental.setRentedAt(LocalDateTime.now());

        Rental saved = rentals.save(rental);

        return toResponse(saved);
    }

    @Transactional
    public RentalResponse returnVideo(
            String email,
            Long rentalId) {

        AppUser user = getUser(email);

        // Find active rental belonging to this user.
        Rental rental = rentals
                .findByIdAndUserIdAndReturnedAtIsNull(
                        rentalId,
                        user.getId()
                )
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Active rental not found for this customer"
                        )
                );

        // Set return timestamp.
        rental.setReturnedAt(LocalDateTime.now());

        // Make video available again.
        Video video = rental.getVideo();

        video.setAvailable(true);

        videos.save(video);

        return toResponse(rentals.save(rental));
    }

    public List<RentalResponse> active(
            String email) {

        AppUser user = getUser(email);

        return rentals
                .findByUserIdAndReturnedAtIsNull(
                        user.getId()
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AppUser getUser(String email) {

        return users.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );
    }

    private RentalResponse toResponse(
            Rental rental) {

        return new RentalResponse(
                rental.getId(),
                rental.getVideo().getId(),
                rental.getVideo().getTitle(),
                rental.getRentedAt(),
                rental.getReturnedAt()
        );
    }
}