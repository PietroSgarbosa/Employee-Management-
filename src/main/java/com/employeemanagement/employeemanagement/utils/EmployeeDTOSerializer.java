package com.employeemanagement.employeemanagement.utils;

import java.io.IOException;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmployeeDTOSerializer extends JsonSerializer<EmployeeDTO> {
    @Override
    public void serialize(EmployeeDTO employeeDTO, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", employeeDTO.getId());
        gen.writeStringField("fullName", employeeDTO.getFullName());
        gen.writeStringField("admissionDate", employeeDTO.getAdmissionDate().toString());
        gen.writeStringField("photo", employeeDTO.getPhoto());
        gen.writeStringField("rg", employeeDTO.getRg());
        gen.writeStringField("cpf", employeeDTO.getCpf());
        if (employeeDTO.getCategory() != null) {
            gen.writeObjectField("category", employeeDTO.getCategory().getDescription());
        }

        gen.writeEndObject();
    }
}
