package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.CollaboratorDTO;
import com.employeemanagement.employeemanagement.entity.Collaborator;

@Component
public class CollaboratorMapper {

	public Collaborator covertToEntity(CollaboratorDTO dto) {
		Collaborator entity = new Collaborator();
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setCpf(dto.getCpf());
		entity.setCategory(dto.getCategory());
		
		return entity;
	}
	
}
