package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.OccupationalHealthCertificateType;

public class OccupationalHealthCertificateTypeDTO {
	
	private Long id;
	
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static OccupationalHealthCertificateTypeDTO convertToDTO(OccupationalHealthCertificateType entity) {
		return getModelMapper().map(entity, OccupationalHealthCertificateTypeDTO.class);
	}

}
