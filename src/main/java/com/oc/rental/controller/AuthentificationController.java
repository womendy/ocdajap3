package com.oc.rental.controller;

import com.oc.rental.dto.TokenDto;
import com.oc.rental.dto.UserDto;
import com.oc.rental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController("auth/")
public class AuthentificationController {

  private final UserService userService;


  @PostMapping("register")
  public TokenDto register(@RequestBody UserDto userDto) {
    if (userService.findUserByEmail(userDto.getEmail()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
    }
    return new TokenDto(userService.registerUser(userDto)) ;
  }

  @PostMapping("login")
  public TokenDto login(@RequestBody UserDto userDto) {
    return new TokenDto(userService.loginUser(userDto));
  }
}

