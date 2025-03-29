package com.github.concur.mapper;

import com.github.concur.dto.UserDTO;
import com.github.concur.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

  @Mapping(target = "passwordHash", ignore = true)
  @Mapping(target = "userRole", ignore = true)
  User toEntity(UserDTO userDTO);

}
