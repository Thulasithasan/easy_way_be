package com.thulasi.easyway.util.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = false)
public class StringListConverter implements AttributeConverter<List<Object>, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<Object> attribute) {
		if (attribute == null) {
			return "[]";
		}
		try {
			return objectMapper.writeValueAsString(attribute);
		}
		catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Error converting list to JSON string.", e);
		}
	}

	@Override
	public List<Object> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) {
			return new ArrayList<>();
		}
		try {
			return objectMapper.readValue(dbData, new TypeReference<List<Object>>() {
			});
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error reading JSON string to list.", e);
		}
	}

}
