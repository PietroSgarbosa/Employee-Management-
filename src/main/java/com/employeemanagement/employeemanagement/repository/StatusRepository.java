package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
