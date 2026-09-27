package com.midhunn.rentvideo.dto;
import com.midhunn.rentvideo.entity.Role; import jakarta.validation.constraints.*;
public class RegisterRequest {
 @Email @NotBlank private String email; @NotBlank private String password; @NotBlank private String firstName; @NotBlank private String lastName; private Role role;
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
 public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;}
}
