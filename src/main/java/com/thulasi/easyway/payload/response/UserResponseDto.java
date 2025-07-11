package com.thulasi.easyway.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

	private Long userId;

	private String email;

	private String firstName;

	private String lastName;

	private RoleResponseDto roleResponseDto;

	private Integer totalXp;

	private Integer totalChallenges;

	private Integer currentLevel;

	private double avarageSpeed;

	private double avarageAccuracy;

	private double bestSpeed;

	private double bestAccuracy;

	private String currentBadgeName;

	private String currentBadgeDescription;

	private Integer timespent;

}
