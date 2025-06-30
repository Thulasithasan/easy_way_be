package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.component.AsyncEmailSender;
import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.mapper.CommonMapper;
import com.thulasi.easyway.model.Role;
import com.thulasi.easyway.model.RolePermission;
import com.thulasi.easyway.model.User;
import com.thulasi.easyway.payload.request.*;
import com.thulasi.easyway.payload.response.*;
import com.thulasi.easyway.repository.UserRepository;
import com.thulasi.easyway.service.AuthService;
import com.thulasi.easyway.service.JwtService;
import com.thulasi.easyway.service.UserService;
import com.thulasi.easyway.util.Validation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final UserDetailsService userDetailsService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final CommonMapper commonMapper;
	private final UserService userService;
	private final AsyncEmailSender asyncEmailSender;

	@Value("${encryptDecryptAlgorithm.secret}")
	private String encryptSecret;

	@Value("${initial.xp}")
	private String initialXp;

	@Override
	public ResponseEntityDto signUp(SignUpRequestDto signUpRequestDto) {
		log.info("signUp: execution started");

		if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_ALREADY_EXISTS);
		}

		Validation.isValidFirstName(signUpRequestDto.getFirstName());
		Validation.isValidLastName(signUpRequestDto.getLastName());
		Validation.validateEmail(signUpRequestDto.getEmail());

		String generatedPassword = generatePassword();

		User user = new User();
		user.setFirstName(signUpRequestDto.getFirstName());
		user.setLastName(signUpRequestDto.getLastName());
		user.setEmail(signUpRequestDto.getEmail());
		user.setPhoneNumber(signUpRequestDto.getPhoneNumber());
		user.setAddress(signUpRequestDto.getAddress());
		user.setCity(signUpRequestDto.getCity());
		user.setDistrict(signUpRequestDto.getDistrict());
		user.setProvince(signUpRequestDto.getProvince());
		user.setPassword(passwordEncoder.encode(generatedPassword));
		user.setIsPasswordChangedForTheFirstTime(false);
		user.setRole(signUpRequestDto.getRoleId() == null ? Role.builder().id(3L).build()
				: Role.builder().id(signUpRequestDto.getRoleId()).build());

		userRepository.save(user);

		String subject = "Welcome to Kothai! Your Account Details";

		String htmlBody = "<html>" + "<body style='font-family: Arial, sans-serif; color: #333;'>" + "<h2>Welcome, "
				+ user.getFirstName() + " " + user.getLastName() + "!</h2>"
				+ "<p>We're excited to have you on board.</p>" + "<p>Your account has been created successfully.</p>"
				+ "<p><strong>Your temporary password:</strong> <span style='color: #d6336c;'>" + generatedPassword
				+ "</span></p>" + "<p>Please make sure to change your password after your first login.</p>" + "<br>"
				+ "<p>Best regards,<br><strong>Kothai Team</strong></p>" + "</body>" + "</html>";

		asyncEmailSender.sendMail(user.getEmail(), subject, htmlBody);

		log.info("signUp: execution ended");
		return new ResponseEntityDto("Password has been sent to your email. Please check.", true);
	}

	@Override
	public ResponseEntityDto signIn(SignInRequestDto signInRequestDto) {
		log.info("signIn: execution started");

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

		Optional<User> optionalUser = userRepository.findByEmail(signInRequestDto.getEmail());
		if (optionalUser.isEmpty()) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND);
		}
		User user = optionalUser.get();

		if (Boolean.FALSE.equals(user.getIsActive())) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_ACCOUNT_DEACTIVATED);
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		String accessToken = jwtService.generateAccessToken(userDetails, user.getId());
		String refreshToken = jwtService.generateRefreshToken(userDetails);

		SignInResponseDto signInResponseDto = commonMapper.createUserToSignInResponseDto(user);
		signInResponseDto.setAccessToken(accessToken);
		signInResponseDto.setRefreshToken(refreshToken);
		signInResponseDto.setRoleResponseDto(createRoleResponseDto(user.getRole()));

		log.info("signIn: execution ended");
		return new ResponseEntityDto(true, signInResponseDto);
	}

	@Transactional
	public SignInResponseDto handleGoogleLogin(String email, String firstName, String lastName) {
		log.info("handleGoogleLogin: execution started");
		Optional<User> userOpt = userRepository.findByEmail(email);
		User user;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		else {
			user = new User();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setRole(Role.builder().id(3L).build());

			userRepository.save(user);
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		String accessToken = jwtService.generateAccessToken(userDetails, user.getId());
		String refreshToken = jwtService.generateRefreshToken(userDetails);

		SignInResponseDto signInResponseDto = commonMapper.createUserToSignInResponseDto(user);
		signInResponseDto.setAccessToken(accessToken);
		signInResponseDto.setRefreshToken(refreshToken);
		signInResponseDto.setRoleResponseDto(createRoleResponseDto(user.getRole()));

		log.info("handleGoogleLogin: execution ended");
		return signInResponseDto;
	}

	@Override
	public ResponseEntityDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
		log.info("refreshAccessToken: execution started");

		if (!jwtService.isRefreshToken(refreshTokenRequestDto.getRefreshToken())
				|| jwtService.isTokenExpired(refreshTokenRequestDto.getRefreshToken())) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_INVALID_REFRESH_TOKEN);
		}

		String userEmail = jwtService.extractUserEmail(refreshTokenRequestDto.getRefreshToken());
		UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

		if (!jwtService.isTokenValid(refreshTokenRequestDto.getRefreshToken(), userDetails)) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_INVALID_REFRESH_TOKEN);
		}

		Optional<User> optionalUser = userRepository.findByEmail(userEmail);
		if (optionalUser.isEmpty()) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND);
		}
		User user = optionalUser.get();

		UserResponseDto userToUserResponseDto = commonMapper.createUserToUserResponseDto(user);
		userToUserResponseDto.setRoleResponseDto(createRoleResponseDto(user.getRole()));
		String accessToken = jwtService.generateAccessToken(userDetails, user.getId());

		AccessTokenResponseDto accessTokenResponseDto = new AccessTokenResponseDto();
		accessTokenResponseDto.setAccessToken(accessToken);
		accessTokenResponseDto.setUserResponseDto(userToUserResponseDto);

		log.info("refreshAccessToken: execution ended");
		return new ResponseEntityDto(false, accessTokenResponseDto);
	}

	@Override
	public ResponseEntityDto employeeResetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
		log.info("employeeResetPassword: execution started");

		User user = userService.getCurrentUser();
		if (user == null) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND);
		}

		if (Boolean.TRUE.equals(user.getIsPasswordChangedForTheFirstTime())) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_ALREADY_PASSWORD_RESET);
		}

		String newPassword = resetPasswordRequestDto.getNewPassword();
		createNewPassword(newPassword, user);

		log.info("employeeResetPassword: execution ended");
		return new ResponseEntityDto(false, "User password reset successfully");
	}

	@Override
	public ResponseEntityDto changePassword(ChangePasswordRequestDto changePasswordRequestDto, Long userId) {
		log.info("changePassword: execution started");

		User user = userService.getCurrentUser();
		if (!Objects.equals(user.getId(), userId)) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND);
		}

		if (!passwordEncoder.matches(changePasswordRequestDto.getOldPassword(), user.getPassword())) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_OLD_PASSWORD_INCORRECT);
		}

		String newPassword = changePasswordRequestDto.getNewPassword();
		createNewPassword(newPassword, user);

		log.info("changePassword: execution ended");
		return new ResponseEntityDto(false, "User password changed successfully");
	}

	private void createNewPassword(String newPassword, User user) {
		if (user.getPreviousPasswordsList()
			.stream()
			.anyMatch(prevPassword -> passwordEncoder.matches(newPassword, prevPassword))) {
			throw new ModuleException(CommonMessageConstant.COMMON_ERROR_CANNOT_USE_PREVIOUS_PASSWORDS);
		}

		String encodedNewPassword = passwordEncoder.encode(newPassword);

		if (user.getPassword() != null) {
			user.addPreviousPassword(user.getPassword());
		}

		user.setPassword(encodedNewPassword);
		user.setIsPasswordChangedForTheFirstTime(true);

		userRepository.save(user);
	}

	private String generatePassword() {
		String tempPassword = UUID.randomUUID().toString().substring(0, 8);
		log.info("tempPassword: {}", tempPassword);
		return tempPassword;
	}

	private RoleResponseDto createRoleResponseDto(Role role) {
		return RoleResponseDto.builder()
			.roleId(role.getId())
			.name(role.getName())
			.description(role.getDescription())
			.permissions(role.getRolePermissions().stream().map(this::createPermissionResponseDto).toList())
			.build();
	}

	private PermissionResponseDto createPermissionResponseDto(RolePermission rolePermission) {
		return PermissionResponseDto.builder()
			.permissionId(rolePermission.getPermission().getId())
			.name(rolePermission.getPermission().getName())
			.description(rolePermission.getPermission().getDescription())
			.subPermissions(rolePermission.getSubPermissions())
			.build();
	}

}
