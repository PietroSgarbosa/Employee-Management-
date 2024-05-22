package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;

public class TrainingEmployeeDTO {

	private Training idTraining;
	
	private Status idStatus;
	
	public Training getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Training idTraining) {
		this.idTraining = idTraining;
	}

	public Status getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Status idStatus) {
		this.idStatus = idStatus;
	}

	static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    public static TrainingEmployeeDTO convertToDTO(TrainingEmployee entity) {
        return getModelMapper().map(entity, TrainingEmployeeDTO.class);
    }
}
