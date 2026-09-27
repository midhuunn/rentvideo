package com.midhunn.rentvideo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity @Table(name="videos")
public class Video {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String title; @NotBlank private String director; @NotBlank private String genre;
 @Column(nullable=false) private boolean available=true;
 public Video(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getDirector(){return director;} public void setDirector(String v){director=v;}
 public String getGenre(){return genre;} public void setGenre(String v){genre=v;}
 public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
}
