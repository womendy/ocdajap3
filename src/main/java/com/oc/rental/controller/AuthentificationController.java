package com.oc.rental.controller;

import com.oc.rental.dto.TokenDto;
import com.oc.rental.dto.UserDto;
import com.oc.rental.models.User;
import com.oc.rental.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/")
public class AuthentificationController {

    private final UserService userService;


    @PostMapping("register")
    public TokenDto register(@RequestBody UserDto userDto) {
        if (userService.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return new TokenDto(userService.registerUser(userDto));
    }

    @PostMapping("login")
    public TokenDto login(@RequestBody UserDto userDto) {
        return new TokenDto(userService.loginUser(userDto));
    }


    @GetMapping("me")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "User found", response = UserDto.class),
//            @ApiResponse(code = 401, message = "Unauthorized")
//    })
    public UserDetails getAuthenticatedUser(@AuthenticationPrincipal UserDetails userPrincipal) {
        if (userPrincipal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
     return userPrincipal;
    }

}



