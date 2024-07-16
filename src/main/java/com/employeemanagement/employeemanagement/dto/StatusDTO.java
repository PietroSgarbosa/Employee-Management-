package com.employeemanagement.employeemanagement.dto;

import com.employeemanagement.employeemanagement.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class StatusDTO {

	private Long id;

	@JsonView(Views.Basic.class)
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

}
