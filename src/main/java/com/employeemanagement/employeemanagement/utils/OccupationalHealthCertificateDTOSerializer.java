package com.employeemanagement.employeemanagement.utils;

import java.io.IOException;

import com.employeemanagement.employeemanagement.dto.OccupationalHealthCertificateDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class OccupationalHealthCertificateDTOSerializer extends JsonSerializer<OccupationalHealthCertificateDTO> {

	@Override
	public void serialize(OccupationalHealthCertificateDTO dto, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartObject();

		gen.writeNumberField("id", dto.getId());
		if (dto.getEmployee() != null) {
			gen.writeObjectField("employee", dto.getEmployee().getFullName());
		}

		gen.writeStringField("date", dto.getDate().toString());

		if(dto.getOccupationHealthCertificateType() != null) {
			gen.writeObjectField("occupationHealthCertificateType", dto.getOccupationHealthCertificateType());
		}

		if (dto.isAccomplished() == true) {
			gen.writeStringField("isAccomplished", "Yes");
		} else {
			gen.writeStringField("isAccomplished", "No");
		}

		if (dto.getCurrentCategory() != null) {
			gen.writeObjectField("currentCategory", dto.getCurrentCategory().getDescription());
		}

		if (dto.getNewCategory() != null) {
			gen.writeObjectField("newCategory", dto.getNewCategory().getDescription());
		}

		gen.writeEndObject();
	}

}
