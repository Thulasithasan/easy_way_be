package com.thulasi.easyway.security;

import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.payload.response.SignInResponseDto;
import com.thulasi.easyway.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

	private final AuthService authService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
		String email = oauthUser.getAttribute("email");
		String firstName = oauthUser.getAttribute("given_name"); // First name
		String lastName = oauthUser.getAttribute("family_name"); // Last name

		// Generate your JWT or app-specific token
		SignInResponseDto signInResponseDto = authService.handleGoogleLogin(email, firstName, lastName);

		String redirectUrl = String.format("http://localhost:3000/auth/google/callback?accessToken=%s&refreshToken=%s",
				// "http://localhost:3000/categories?accessToken=%s&refreshToken=%s",
				signInResponseDto.getAccessToken(), signInResponseDto.getRefreshToken());

		response.sendRedirect(redirectUrl);
	}

}
