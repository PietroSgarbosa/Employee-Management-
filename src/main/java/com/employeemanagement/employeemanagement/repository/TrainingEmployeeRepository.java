package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeKey;

@Repository
public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, TrainingEmployeeKey>{

}
