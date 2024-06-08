package com.oc.rental.service;

import com.oc.rental.dto.UserDto;
import com.oc.rental.models.User;

import java.util.Optional;


public interface UserService {

  public Optional<User> findUserByEmail(String email);
  public Optional<User> findUserById(Long id);
  public String registerUser(UserDto user);
  public String loginUser(UserDto user);

}
