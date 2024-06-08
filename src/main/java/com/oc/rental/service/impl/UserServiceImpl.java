package com.oc.rental.service.impl;

import com.oc.rental.configuration.JwtUtil;
import com.oc.rental.dto.UserDto;
import com.oc.rental.models.User;
import com.oc.rental.repository.impl.UserRepository;
import com.oc.rental.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JwtUtil jwtUtil;
  private AuthenticationManager authenticationManager;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.authenticationManager = authenticationManager;

  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  @Override
  public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public String registerUser(UserDto user) {
    User newUser = new User();
    newUser.setName(user.getName());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(newUser);
    return jwtUtil.generateToken(user.getEmail());
  }


  public String loginUser(UserDto user) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtUtil.generateToken(user.getEmail());
  }


}
