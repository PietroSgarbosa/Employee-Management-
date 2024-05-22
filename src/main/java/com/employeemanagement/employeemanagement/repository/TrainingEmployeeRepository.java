package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeId;

public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, TrainingEmployeeId>{

}
