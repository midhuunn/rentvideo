package com.midhunn.rentvideo.security;
import org.springframework.context.annotation.*; import org.springframework.http.HttpMethod; import org.springframework.security.config.Customizer; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
 http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth
 .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
 .requestMatchers(HttpMethod.GET,"/api/videos/available").hasAnyRole("CUSTOMER","ADMIN")
 .requestMatchers(HttpMethod.GET,"/api/videos","/api/videos/*").hasAnyRole("CUSTOMER","ADMIN")
 .requestMatchers("/api/videos/**").hasRole("ADMIN").anyRequest().authenticated())
 .httpBasic(Customizer.withDefaults()); return http.build();}
}
