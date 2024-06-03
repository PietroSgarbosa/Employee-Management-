package com.employeemanagement.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.Training;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository<EmployeeTraining, Long> {
	
	List<EmployeeTraining> getBytraining(Training training); 

}
