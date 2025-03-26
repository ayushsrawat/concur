package com.github.concur.controller;

import com.github.concur.dto.LoginRequest;
import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import com.github.concur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class AuthUserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
    try {
      User user = userService.registerUser(userDTO);
      return ResponseEntity.ok(user.getUsername() + " registered successfully.");
    } catch (IllegalArgumentException iae) {
      return ResponseEntity.badRequest().body(iae.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String token = userService.login(request);
    if (token == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
    }
    return ResponseEntity.ok(Map.of("token", token));
  }

}
