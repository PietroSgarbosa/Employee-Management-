package com.employeemanagement.employeemanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingKey;
import com.employeemanagement.employeemanagement.entity.Training;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository<EmployeeTraining, EmployeeTrainingKey> {

	List<EmployeeTraining> getBytraining(Training training);
	List<EmployeeTraining> getByEmployee(Employee employee);

}