package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingPK;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository <EmployeeTraining, EmployeeTrainingPK>{

}
