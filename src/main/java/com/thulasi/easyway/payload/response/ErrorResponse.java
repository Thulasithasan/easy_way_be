package com.thulasi.easyway.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thulasi.easyway.constant.MessageConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	private int code;

	private String status;

	private String messageKey;

	private String message;

	private List<ValidationError> errors;

	public ErrorResponse() {
		timestamp = new Date();
	}

	public ErrorResponse(HttpStatus httpStatus, String message, MessageConstant messageKey) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.messageKey = messageKey != null ? messageKey.name() : null;
		this.message = message;
	}

	public void addValidationError(String field, String message) {
		if (Objects.isNull(errors)) {
			errors = new ArrayList<>();
		}
		errors.add(new ValidationError(field, message));
	}

	private record ValidationError(String field, String message) {
	}

}
