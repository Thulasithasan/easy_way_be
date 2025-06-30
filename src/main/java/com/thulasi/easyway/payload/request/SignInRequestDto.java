package com.thulasi.easyway.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {

	@Schema(description = "User's email address", example = "superadmin@example.com")
	@Email(message = "Invalid email format")
	@NotNull
	private String email;

	@Schema(description = "User's password", example = "Kothai@31052025")
	@NotNull
	private String password;

}
