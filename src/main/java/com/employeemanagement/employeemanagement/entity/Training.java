package com.employeemanagement.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRAINING")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	    
	@Column(name = "DESCRIPTION", nullable = false, unique = true)
	private String description;

	public Long getID() {
		return id;
	}

	public void setID(Long iDTraining) {
		id = iDTraining;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
