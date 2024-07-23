package com.employeemanagement.employeemanagement.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.employeemanagement.employeemanagement.entity.OccupationalHealthCertificate;
import com.employeemanagement.employeemanagement.utils.OccupationalHealthCertificateDTOSerializer;
import com.employeemanagement.employeemanagement.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = OccupationalHealthCertificateDTOSerializer.class)
public class OccupationalHealthCertificateDTO {
	
	@JsonView(Views.Basic.class)
	private Long id;
	
	@JsonView(Views.Basic.class)
    private Long employeeId;
    
	@JsonView(Views.Basic.class)
    private EmployeeDTO employee;
    
	@JsonView(Views.Basic.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    
	@JsonView(Views.Detailed.class)
    private long occupationHealthCertificateTypeId;
	
	@JsonView(Views.Basic.class)
    private OccupationalHealthCertificateTypeDTO occupationHealthCertificateType;
    
	@JsonView(Views.Basic.class)
    private boolean isAccomplished;
    
	@JsonView(Views.Basic.class)
    private CategoryDTO currentCategory;
    
	@JsonView(Views.Basic.class)
    private Long newCategoryId;
    
	@JsonView(Views.Basic.class)
    private CategoryDTO newCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OccupationalHealthCertificateTypeDTO getOccupationHealthCertificateType() {
		return occupationHealthCertificateType;
	}

	public void setOccupationHealthCertificateType(OccupationalHealthCertificateTypeDTO occupationHealthCertificateType) {
		this.occupationHealthCertificateType = occupationHealthCertificateType;
	}

	public boolean isAccomplished() {
		return isAccomplished;
	}

	public void setAccomplished(boolean isAccomplished) {
		this.isAccomplished = isAccomplished;
	}

	public Long getNewCategoryId() {
		return newCategoryId;
	}

	public void setNewCategoryId(Long newCategoryId) {
		this.newCategoryId = newCategoryId;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public CategoryDTO getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(CategoryDTO currentCategory) {
		this.currentCategory = currentCategory;
	}

	public CategoryDTO getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(CategoryDTO newCategory) {
		this.newCategory = newCategory;
	}
   
	public long getOccupationHealthCertificateTypeId() {
		return occupationHealthCertificateTypeId;
	}

	public void setOccupationHealthCertificateTypeId(long occupationHealthCertificateTypeId) {
		this.occupationHealthCertificateTypeId = occupationHealthCertificateTypeId;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static OccupationalHealthCertificateDTO convertToDTO(OccupationalHealthCertificate entity) {
		return getModelMapper().map(entity, OccupationalHealthCertificateDTO.class);
	}
}
