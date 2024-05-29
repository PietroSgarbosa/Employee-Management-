package com.employeemanagement.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeKey;

@Repository
public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, TrainingEmployeeKey>{

	List<TrainingEmployee> getByidEmployee(Employee idEmployee);
	List<TrainingEmployee> getByidTraining(Training idTraining);
}
