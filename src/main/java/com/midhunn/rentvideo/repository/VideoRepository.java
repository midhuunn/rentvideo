package com.midhunn.rentvideo.repository;
import com.midhunn.rentvideo.entity.Video; import java.util.List; import org.springframework.data.jpa.repository.JpaRepository;
public interface VideoRepository extends JpaRepository<Video,Long>{List<Video> findByAvailableTrue();}
