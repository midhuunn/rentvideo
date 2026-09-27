package com.midhunn.rentvideo.service;

import com.midhunn.rentvideo.dto.VideoRequest;
import com.midhunn.rentvideo.models.Video;
import com.midhunn.rentvideo.repository.VideoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<Video> available() {
        return repository.findByAvailableTrue();
    }

    public Video create(VideoRequest request) {

        Video video = new Video();

        apply(video, request);

        video.setAvailable(true);

        return repository.save(video);
    }

    public Video update(Long id, VideoRequest request) {

        Video video = find(id);

        apply(video, request);

        return repository.save(video);
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Video find(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Video not found"
                        )
                );
    }

    private void apply(
            Video video,
            VideoRequest request) {

        video.setTitle(request.title());
        video.setDirector(request.director());
        video.setGenre(request.genre());
    }
}