package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.*;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.payload.response.SignInResponseDto;

public interface AuthService {

	ResponseEntityDto signIn(SignInRequestDto signInRequestDto);

	ResponseEntityDto signUp(SignUpRequestDto signUpRequestDto);

	SignInResponseDto handleGoogleLogin(String email, String firstName, String lastName);

	ResponseEntityDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

	ResponseEntityDto employeeResetPassword(ResetPasswordRequestDto resetPasswordRequestDto);

	ResponseEntityDto changePassword(ChangePasswordRequestDto changePasswordRequestDto, Long userId);

}
