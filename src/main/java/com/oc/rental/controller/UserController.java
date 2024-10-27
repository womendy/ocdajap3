package com.oc.rental.controller;

import com.oc.rental.dto.UserDto;
import com.oc.rental.mapper.UserMapper;
import com.oc.rental.models.User;
import com.oc.rental.service.UserService; // Import your user service
import com.sun.security.auth.UserPrincipal;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // For ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*; // For RestController and request mapping
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")

public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "User found", response = UserDto.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "User not found", response = ErrorResponse.class)
  })
  public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable long id) {
    Optional<UserDto> userDto = UserMapper.toDto(userService.findUserById(id));
    if (userDto != null) {
      return ResponseEntity.ok(userDto);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("me")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "User found", response = UserDto.class),
          @ApiResponse(code = 401, message = "Unauthorized")
  })
  public UserDto getAuthenticatedUser(@AuthenticationPrincipal User userPrincipal) {
    if (userPrincipal == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
    UserDto userDto = new UserDto(
            userPrincipal.getId(),
            userPrincipal.getName(),
            userPrincipal.getEmail()
    );

    return userDto;


  }
}

