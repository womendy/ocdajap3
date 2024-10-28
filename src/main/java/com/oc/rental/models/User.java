package com.oc.rental.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
@Setter
@Entity
public class User extends CoreEntity implements UserDetails {

  @Email
  @Column(nullable = false, unique = true)
  private String email;
  @OneToMany(mappedBy = "owner")
  private List<Rental> rentalList;
  @OneToMany(mappedBy = "user")
  private List<Message> messages;
  @Column(nullable = false)
  private String password;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
