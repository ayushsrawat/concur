package com.github.concur.service.impl;

import com.github.concur.dto.LoginRequest;
import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import com.github.concur.repository.UserRepository;
import com.github.concur.service.UserService;
import com.github.concur.util.DateUtil;
import com.github.concur.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final DateUtil dateUtil;

  @Override
  public User registerUser(UserDTO userDTO) {
    int age = dateUtil.dateDifference(new Date(), userDTO.getDob());
    if (age <= 12) {
      throw new IllegalArgumentException("Age restriction: Users must be older than 12 years to proceed. Please ensure the age entered is correct.");
    }
    if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
      throw new IllegalArgumentException("Username already taken.");
    }
    try {
      User user = new User();
      user.setUsername(userDTO.getUsername());
      user.setFirstName(userDTO.getFirstName());
      user.setLastName(userDTO.getLastName());
      user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
      user.setDob(userDTO.getDob());
      user.setPhone(userDTO.getPhone());
      user.setEmail(userDTO.getEmail());
      user.setAddress(userDTO.getAddress());
      user.setCreatedAt(LocalDateTime.now());
      return userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Error registering the user. Please ensure all the details are valid.");
    }
  }

  @Override
  public String login(LoginRequest request) {
    Optional<User> user = userRepository.findByUsername(request.getUsername());
    if (user.isPresent()) {
      if (passwordEncoder.matches(request.getPassword(), user.get().getPasswordHash())) {
        return jwtUtil.generateToken(request.getUsername());
      }
    }
    return null;
  }

}
