package com.thulasi.easyway.payload.response;

import com.thulasi.easyway.type.HealthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthResponse {

	private HealthStatus status;

	private String timestamp;

	private String version;

}
