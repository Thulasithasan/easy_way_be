package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.mapper.CommonMapper;
import com.thulasi.easyway.model.User;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.payload.response.UserResponseDto;
import com.thulasi.easyway.repository.UserRepository;
import com.thulasi.easyway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CommonMapper commonMapper;

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		return userRepository.findByEmail(email)
			.orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND));
	}

	@Override
	public Optional<User> getOptionalCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		return userRepository.findByEmail(email);
	}

	@Override
	public ResponseEntityDto getCurrentUserDetails() {
		log.info("getCurrentUserDetails: execution started");
		User currentUser = getCurrentUser();
		UserResponseDto userResponseDto = commonMapper.createUserToUserResponseDto(currentUser);

		log.info("getCurrentUserDetails: execution ended");
		return new ResponseEntityDto(true, userResponseDto);
	}

}
