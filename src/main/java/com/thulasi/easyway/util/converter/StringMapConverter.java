package com.thulasi.easyway.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = false)
public class StringMapConverter implements AttributeConverter<Map<String, String>, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Map<String, String> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return "{}";
		}
		try {
			return objectMapper.writeValueAsString(attribute);
		}
		catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Error converting map to JSON string.", e);
		}
	}

	@Override
	public Map<String, String> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isEmpty()) {
			return new HashMap<>();
		}
		try {
			return objectMapper.readValue(dbData, new TypeReference<>() {
			});
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error reading JSON string to map.", e);
		}
	}

}
