package com.github.concur.service;

import com.github.concur.dto.LoginRequest;
import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;

public interface UserService {

  /**
   * Sign-up a new user. By default all the users(customer, seller & admins) are registered as customers.
   * Later they can upgrade to seller/admins based on authority they have.
   */
  User registerUser(UserDTO userDTO);

  String login(LoginRequest request);

}
