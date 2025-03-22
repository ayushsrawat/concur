package com.github.concur.service;

import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import com.github.concur.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

  @InjectMocks
  private UserService userService;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void registerUserTest() {

    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("ayush");
    userDTO.setPassword("password123");
    userDTO.setFirstName("Ayush");
    userDTO.setLastName("Rawat");
    userDTO.setAge(25);
    userDTO.setEmail("ayushtest@gmail.com");
    userDTO.setAddress("Uttarakhand");

    userService.registerUser(userDTO);

    Optional<User> byUsername = userRepository.findByUsername(userDTO.getUsername());
    assert byUsername.isPresent();
    logger.info("{} saved successfully", userDTO.getUsername());
    String passwordHash = passwordEncoder.encode(userDTO.getPassword());
    logger.info("password hash : {}", passwordHash);
    assert byUsername.get().getPasswordHash().equals(passwordHash);
    logger.info("test successful!!");
  }
}
