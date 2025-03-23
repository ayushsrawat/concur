package com.github.concur.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private Date   dob;
  private String phone;
  private String email;
  private String address;

}
