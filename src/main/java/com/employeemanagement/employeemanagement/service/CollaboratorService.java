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
	
	public Collaborator getById(Long id) {
		return getCollaboratorRepository().findById(id).orElse(null);
	}
	
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
	
	//Atualizando por ID
	public String update(CollaboratorDTO collaboratorDTO) {
		Collaborator defaultCollaborator = getById(collaboratorDTO.getId());
		String responseMessage = "Collaborator of ID " + collaboratorDTO.getId() + " not found";

		if(defaultCollaborator != null) {
			defaultCollaborator.setFirstName(collaboratorDTO.getFirstName());
			defaultCollaborator.setMiddleName(collaboratorDTO.getMiddleName());
			defaultCollaborator.setLastName(collaboratorDTO.getLastName());
			defaultCollaborator.setCpf(collaboratorDTO.getCpf());
			defaultCollaborator.setCategory(collaboratorDTO.getCategory());
			save(CollaboratorDTO.convertToDTO(defaultCollaborator));
			responseMessage = "Collaborator of ID " + collaboratorDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	
	//Delete por ID
	public String delete(Long id) {
		Collaborator collaborator = getById(id);
		
		if(collaborator == null) {
			return "This collaborator ID " + id + " doesn't exist";
		} else {
			getCollaboratorRepository().deleteById(id);
			return "Collaborator of ID " + id + " removed!";
		}
	}
 	
	private CollaboratorRepository getCollaboratorRepository() {
		return collaboratorRepository;
	}
	
	private CollaboratorMapper getCollaboratorMapper() {
		return collaboratorMapper;
	}

}
