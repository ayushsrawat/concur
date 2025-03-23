package com.github.concur.service;

import com.github.concur.dto.LoginRequest;
import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;

public interface UserService {

  User registerUser(UserDTO userDTO);

  String login(LoginRequest request);

}
