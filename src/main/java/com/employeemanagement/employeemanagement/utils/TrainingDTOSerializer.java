package com.employeemanagement.employeemanagement.utils;

import java.io.IOException;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TrainingDTOSerializer extends JsonSerializer<TrainingDTO> {
	@Override
	public void serialize(TrainingDTO trainingDTO, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", trainingDTO.getId());
		gen.writeStringField("title", trainingDTO.getTitle());
		gen.writeStringField("description", trainingDTO.getDescription());
		if (trainingDTO.getCategoryDTO() != null) {
			gen.writeObjectField("category", trainingDTO.getCategoryDTO().getDescription());
		}
		if (trainingDTO.getStatus() != null) {
			gen.writeObjectField("status", trainingDTO.getStatus().getDescription());
		}

		gen.writeEndObject();
	}
}
