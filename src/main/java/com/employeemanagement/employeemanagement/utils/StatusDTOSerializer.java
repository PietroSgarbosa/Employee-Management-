package com.employeemanagement.employeemanagement.utils;

import java.io.IOException;

import com.employeemanagement.employeemanagement.dto.StatusDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StatusDTOSerializer  extends JsonSerializer<StatusDTO> {

	@Override
	public void serialize(StatusDTO value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		jsonGenerator.writeString(value.getDescription());
	}

}
