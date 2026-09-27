package com.midhunn.rentvideo.controller;
import com.midhunn.rentvideo.dto.*; import com.midhunn.rentvideo.entity.*; import com.midhunn.rentvideo.repository.UserRepository; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController {
 private final UserRepository repo; private final PasswordEncoder encoder;
 public AuthController(UserRepository r,PasswordEncoder e){repo=r;encoder=e;}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){
 if(repo.existsByEmail(r.getEmail()))return ResponseEntity.status(409).body(Map.of("error","Email already registered"));
 AppUser u=new AppUser();u.setEmail(r.getEmail());u.setPassword(encoder.encode(r.getPassword()));u.setFirstName(r.getFirstName());u.setLastName(r.getLastName());u.setRole(r.getRole()==null?Role.CUSTOMER:r.getRole());
 return ResponseEntity.status(201).body(UserResponse.from(repo.save(u)));}
 @GetMapping("/login") public Map<String,String> login(){return Map.of("message","Basic authentication successful");}
}
