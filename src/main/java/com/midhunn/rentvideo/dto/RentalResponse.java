package com.midhunn.rentvideo.dto;

import java.time.LocalDateTime;

public record RentalResponse(
        Long rentalId,
        Long videoId,
        String videoTitle,
        LocalDateTime rentedAt,
        LocalDateTime returnedAt
) {}