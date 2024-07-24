package com.employeemanagement.employeemanagement.dto;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.employeemanagement.employeemanagement.entity.Employee;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.employeemanagement.employeemanagement.utils.EmployeeDTOSerializer;
import com.employeemanagement.employeemanagement.utils.Views;

@JsonSerialize(using = EmployeeDTOSerializer.class)
public class EmployeeDTO {

	@JsonView(Views.Basic.class)
	private Long id;

	@JsonView(Views.Basic.class)
	private String fullName;

	@JsonView(Views.Detailed.class)
	private String cpf;
	
	@JsonView(Views.Detailed.class)
	private String rg;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonView(Views.Detailed.class)
	private Date admissionDate;

	@JsonView(Views.Basic.class)
	private CategoryDTO category;
	
	@JsonView(Views.Basic.class)
	private Long categoryId;

	@JsonView(Views.Basic.class)
	private List<Long> trainingsId;

	@JsonView(Views.Basic.class)
	private List<EmployeeTrainingDTO> trainings; 
	
	@JsonView(Views.Basic.class)
	private String photo;
	
	@JsonView(Views.Basic.class)
	private Integer registrationNumber;

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

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
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

	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeDTO convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeDTO.class);
	}

}