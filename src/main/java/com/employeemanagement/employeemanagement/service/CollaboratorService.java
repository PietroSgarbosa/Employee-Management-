package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.CollaboratorDTO;
import com.employeemanagement.employeemanagement.entity.Collaborator;
import com.employeemanagement.employeemanagement.exception.CollaboratorDTOMissingException;
import com.employeemanagement.employeemanagement.exception.CollaboratorNameMissingException;
import com.employeemanagement.employeemanagement.repository.CollaboratorRepository;
import com.employeemanagement.employeemanagement.utils.CollaboratorMapper;

@Service
public class CollaboratorService {
	
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	@Autowired
	private CollaboratorMapper collaboratorMapper;
	
	//Método para buscar uma lista de todos os colaboradores
	public List<CollaboratorDTO> getAll(){
		List<Collaborator> collaboratorList = getCollaboratorRepository().findAll();
		List<CollaboratorDTO> collaboratorListDTO = collaboratorList.stream().map(collaborator -> CollaboratorDTO.convertToDTO(collaborator)).toList();
		
		if(!collaboratorListDTO.isEmpty()) {
			return collaboratorListDTO;
		} else {
			return null;
		}
	}
	
	//Método para inserir um colaborador
	public void save(CollaboratorDTO collaboratorDTO) {
		if(collaboratorDTO != null) {
			if(collaboratorDTO.getFirstName() == null) {
				throw new CollaboratorNameMissingException();
			} else {
				Collaborator collaboratorEntity = getCollaboratorMapper().covertToEntity(collaboratorDTO);
				getCollaboratorRepository().save(collaboratorEntity);
			}
		} else {
			throw new CollaboratorDTOMissingException();
		}
	}
 	
	private CollaboratorRepository getCollaboratorRepository() {
		return collaboratorRepository;
	}
	
	private CollaboratorMapper getCollaboratorMapper() {
		return collaboratorMapper;
	}

}
