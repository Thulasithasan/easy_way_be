package com.thulasi.easyway.mapper;

import com.thulasi.easyway.model.User;
import com.thulasi.easyway.payload.response.SignInResponseDto;
import com.thulasi.easyway.payload.response.UserResponseDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonMapper {

	SignInResponseDto createUserToSignInResponseDto(User user);

	UserResponseDto createUserToUserResponseDto(User user);

}
