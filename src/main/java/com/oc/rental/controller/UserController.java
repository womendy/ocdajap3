package com.oc.rental.controller;

import com.oc.rental.models.User;
import com.oc.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService){this.userService=userService;}

  @GetMapping("/{id}")
  public User getById(@PathVariable("id") long id){return userService.findUserById(id).orElseThrow();}
}
