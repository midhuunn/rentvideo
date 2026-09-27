package com.midhunn.rentvideo.controller;
import com.midhunn.rentvideo.entity.Video; import com.midhunn.rentvideo.exception.ResourceNotFoundException; import com.midhunn.rentvideo.repository.VideoRepository; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/videos") public class VideoController {
 private final VideoRepository repo; public VideoController(VideoRepository r){repo=r;}
 @GetMapping("/available") public List<Video> available(){return repo.findByAvailableTrue();}
 @GetMapping public List<Video> all(){return repo.findAll();}
 @GetMapping("/{id}") public Video get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Video not found: "+id));}
 @PostMapping public ResponseEntity<Video> create(@Valid @RequestBody Video v){v.setId(null);v.setAvailable(true);return ResponseEntity.status(201).body(repo.save(v));}
 @PutMapping("/{id}") public Video update(@PathVariable Long id,@Valid @RequestBody Video in){Video v=get(id);v.setTitle(in.getTitle());v.setDirector(in.getDirector());v.setGenre(in.getGenre());v.setAvailable(in.isAvailable());return repo.save(v);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){repo.delete(get(id));return ResponseEntity.noContent().build();}
}
