package com.employeemanagement.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.repository.CollaboratorRepository;

@Service
public class CollaboratorService {
	
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	private CollaboratorRepository getCollaboratorRepository() {
		return collaboratorRepository;
	}

}
