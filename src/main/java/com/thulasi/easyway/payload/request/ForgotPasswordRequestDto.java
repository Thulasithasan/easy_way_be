package com.thulasi.easyway.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequestDto {

	@NotNull
	private String email;

}
