package com.employeemanagement.employeemanagement.utils;

import java.io.IOException;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategoryDTOSerializer extends JsonSerializer<CategoryDTO> {

	@Override
	public void serialize(CategoryDTO categoryDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeString(categoryDTO.getDescription());
	}

}
