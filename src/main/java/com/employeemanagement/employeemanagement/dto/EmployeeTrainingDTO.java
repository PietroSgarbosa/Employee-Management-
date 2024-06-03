package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.Status;

public class EmployeeTrainingDTO {

    private EmployeeDTO employee;
    private TrainingDTO training;
    private Status status;

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public TrainingDTO getTraining() {
        return training;
    }

    public void setTraining(TrainingDTO training) {
        this.training = training;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Método estático para obter uma instância de ModelMapper
    private static ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    // Método estático para converter de EmployeeTraining para EmployeeTrainingDTO
    public static EmployeeTrainingDTO convertToDTO(EmployeeTraining entity) {
        return getModelMapper().map(entity, EmployeeTrainingDTO.class);
    }
}
