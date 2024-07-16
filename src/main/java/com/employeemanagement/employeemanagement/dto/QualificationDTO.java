package com.employeemanagement.employeemanagement.dto;

import java.util.List;

public class QualificationDTO {
	
	private Long id;

	private String fullName;

	private CategoryDTO category;
	
	private String statusTrainings;

	private List<EmployeeTrainingDTO> trainings;
	
	private String photo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getStatusTrainings() {
		return statusTrainings;
	}

	public void setStatusTrainings(String statusTrainings) {
		this.statusTrainings = statusTrainings;
	}

	public List<EmployeeTrainingDTO> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<EmployeeTrainingDTO> trainings) {
		this.trainings = trainings;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
