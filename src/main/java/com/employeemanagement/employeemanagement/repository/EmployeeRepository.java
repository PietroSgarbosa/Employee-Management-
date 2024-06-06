package com.employeemanagement.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findAll(Specification<Employee> specification);

	List<Employee> findByCategory(Category category);
}
