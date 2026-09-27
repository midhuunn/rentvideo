package com.midhunn.rentvideo.dto;
import com.midhunn.rentvideo.entity.*;
public record UserResponse(Long id,String email,String firstName,String lastName,Role role) {
 public static UserResponse from(AppUser u){return new UserResponse(u.getId(),u.getEmail(),u.getFirstName(),u.getLastName(),u.getRole());}
}
