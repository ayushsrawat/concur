package com.github.concur.util.mapper;

import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  //todo : setup mappers
  User toEntity(UserDTO userDTO);

}
