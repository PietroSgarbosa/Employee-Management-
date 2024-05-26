package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingId;

public interface EmployeeTrainingRepository  extends JpaRepository<EmployeeTraining, EmployeeTrainingId>{

}
