package com.employeemanagement.employeemanagement.repository;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

//	List<Employee> findAll(Specification<Employee> spec);
//
//	//List<EmployeeFilterDTO> findAll(Specification<EmployeeFilterDTO> spec);

}
