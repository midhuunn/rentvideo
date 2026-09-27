package com.midhunn.rentvideo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class AppUser {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Email @NotBlank @Column(nullable=false,unique=true) private String email;
 @Column(nullable=false) private String password;
 @NotBlank @Column(nullable=false) private String firstName;
 @NotBlank @Column(nullable=false) private String lastName;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role=Role.CUSTOMER;
 public AppUser(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
 public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;}
}
