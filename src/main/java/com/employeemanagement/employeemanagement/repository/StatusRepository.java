package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	Status findByDescription(String description);
}
