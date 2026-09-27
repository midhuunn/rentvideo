package com.midhunn.rentvideo.repository;

import com.midhunn.rentvideo.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideoRepository
        extends JpaRepository<Video, Long> {

    List<Video> findByAvailableTrue();
}