package com.thulasi.easyway.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpRequestDto {

	@Schema(description = "User's email address", example = "user@example.com")
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Schema(description = "User's first name", example = "John")
	@NotBlank(message = "First name is required")
	private String firstName;

	@Schema(description = "User's last name", example = "Doe")
	@NotBlank(message = "Last name is required")
	private String lastName;

	@Schema(description = "User's phone number", example = "9876543210")
	@NotBlank(message = "Phone number is required")
	private String phoneNumber;	

	@Schema(description = "User's address", example = "123 Main St, Anytown, USA")
	@NotBlank(message = "Address is required")
	private String address;

	@Schema(description = "User's city", example = "Anytown")
	@NotBlank(message = "City is required")
	private String city;

	@Schema(description = "User's district", example = "Anytown")
	@NotBlank(message = "District is required")
	private String district;

	@Schema(description = "User's province", example = "Anytown")
	@NotBlank(message = "Province is required")
	private String province;

	@Schema(description = "User's role ID", example = "1")
	@NotNull(message = "Role ID is required")
	private Long roleId;

}
