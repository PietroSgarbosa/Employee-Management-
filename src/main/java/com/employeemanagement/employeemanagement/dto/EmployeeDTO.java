package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeDTO {

	private Long id;

	private String fullName;

	private String cpf;
	
	private String rg;
	
	private LocalDate admissionDate;

	private CategoryDTO category;

	private Long categoryId;

	private List<Long> trainingsId;

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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Long> getTrainingsId() {
		return trainingsId;
	}

	public void setTrainingsId(List<Long> trainingsId) {
		this.trainingsId = trainingsId;
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

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeDTO convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeDTO.class);
	}

}