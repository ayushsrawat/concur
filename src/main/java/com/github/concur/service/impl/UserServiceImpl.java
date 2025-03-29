package com.github.concur.service.impl;

import com.github.concur.dto.LoginRequest;
import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import com.github.concur.entity.UserRole;
import com.github.concur.repository.UserRepository;
import com.github.concur.repository.UserRoleRepository;
import com.github.concur.service.UserService;
import com.github.concur.util.DateUtil;
import com.github.concur.util.JwtUtil;
import com.github.concur.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final DateUtil dateUtil;
  private final UserMapper userMapper;

  @Override
  public User registerUser(UserDTO userDTO) {
    int age = dateUtil.dateDifference(new Date(), userDTO.getDob());
    Assert.isTrue(age > 12, "Age restriction: Users must be older than 12 years to proceed. Please ensure the age entered is correct.");
    Assert.isTrue(userRepository.findByUsername(userDTO.getUsername()).isEmpty(), "Username already taken.");
    try {
      User user = userMapper.toEntity(userDTO);
      user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
      user.setCreatedAt(LocalDateTime.now());

      UserRole defaultRole = userRoleRepository.findByName("CUSTOMER")
          .orElseThrow(() -> new IllegalArgumentException("Default role CUSTOMER not found"));
      user.setUserRole(defaultRole);

      logger.info("saving user : {}", userDTO.getUsername());
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
        logger.info("creating jwt token for user : {}", request.getUsername());
        return jwtUtil.generateToken(user.get());
      }
    }
    return null;
  }

}
