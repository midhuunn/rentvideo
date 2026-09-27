package com.midhunn.rentvideo.security;
import com.midhunn.rentvideo.entity.AppUser; import com.midhunn.rentvideo.repository.UserRepository; import org.springframework.security.core.authority.SimpleGrantedAuthority; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service; import java.util.List;
@Service public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository repo; public CustomUserDetailsService(UserRepository r){repo=r;}
 public UserDetails loadUserByUsername(String email){AppUser u=repo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(),List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())));}
}
