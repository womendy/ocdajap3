package com.oc.rental.controller;

import com.oc.rental.dto.UserDto;
import com.oc.rental.mapper.UserMapper;
import com.oc.rental.service.UserService; // Import your user service


import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // For ResponseEntity
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*; // For RestController and request mapping

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
  @Operation(summary = "Récupératon d'un utiisateur par son id", description = "Récupératon d'un utiisateur par son id")
//  @ApiResponses(value = {
//          @ApiResponse(code = 200, message = "User found", response = UserDto.class),
//          @ApiResponse(code = 401, message = "Unauthorized"),
//          @ApiResponse(code = 404, message = "User not found", response = ErrorResponse.class)
//  })
  public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable long id) {
    Optional<UserDto> userDto = UserMapper.toDto(userService.findUserById(id));
    if (userDto != null) {
      return ResponseEntity.ok(userDto);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }


}

