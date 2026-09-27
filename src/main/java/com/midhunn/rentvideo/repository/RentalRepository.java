package com.midhunn.rentvideo.repository;

import com.midhunn.rentvideo.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository
        extends JpaRepository<Rental, Long> {

    long countByUserIdAndReturnedAtIsNull(Long userId);

    boolean existsByVideoIdAndReturnedAtIsNull(Long videoId);

    Optional<Rental> findByIdAndUserIdAndReturnedAtIsNull(
            Long rentalId,
            Long userId
    );

    List<Rental> findByUserIdAndReturnedAtIsNull(Long userId);
}