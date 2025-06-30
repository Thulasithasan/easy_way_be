package com.thulasi.easyway.controller.v1;

import com.thulasi.easyway.payload.request.*;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Tag(name = "Auth Controller", description = "Endpoints for authentication")
public class AuthController {

	@NonNull
	private final AuthService authService;

	@Operation(summary = "Sign Up", description = "Sign up for the application")
	@PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
		ResponseEntityDto response = authService.signUp(signUpRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Sign In", description = "Sign in to the application")
	@PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
		ResponseEntityDto response = authService.signIn(signInRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/google/login")
	@Operation(summary = "Redirect to Google OAuth")
	public void redirectToGoogle(HttpServletResponse response) throws IOException {
		response.sendRedirect("/oauth2/authorization/google");
	}

	@Operation(summary = "Get Access Token Using Refresh Token",
			description = "Obtain a new access token using a refresh token")
	@PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> refreshAccessToken(
			@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
		ResponseEntityDto response = authService.refreshAccessToken(refreshTokenRequestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Reset password", description = "Reset password")
	@PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> employeeResetPassword(
			@Valid @RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
		ResponseEntityDto response = authService.employeeResetPassword(resetPasswordRequestDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/change/password/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> changePassword(@PathVariable Long userId,
			@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
		ResponseEntityDto response = authService.changePassword(changePasswordRequestDto, userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
