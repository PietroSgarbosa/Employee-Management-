package com.employeemanagement.employeemanagement.dto;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.employeemanagement.employeemanagement.utils.CategoryDTOSerializer;
import com.employeemanagement.employeemanagement.utils.Views;

public class EmployeeDTO {

	@JsonView(Views.Basic.class)
	private Long id;

	@JsonView(Views.Basic.class)
	private String fullName;

	@JsonView(Views.Detailed.class)
	private String cpf;
	
	@JsonView(Views.Detailed.class)
	private String rg;
	
	@JsonView(Views.Detailed.class)
	private LocalDate admissionDate;

	@JsonView(Views.Basic.class)
	@JsonSerialize(using = CategoryDTOSerializer.class)
	private CategoryDTO category;
	
	@JsonIgnore
	private Long categoryId;

	@JsonIgnore
	private List<Long> trainingsId;

	@JsonIgnore
	private List<EmployeeTrainingDTO> trainings; 
	
	@JsonView(Views.Basic.class)
	@JsonIgnore
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