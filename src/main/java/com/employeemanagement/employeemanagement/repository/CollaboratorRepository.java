
package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

}
