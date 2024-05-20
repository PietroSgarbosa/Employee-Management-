package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.CollaboratorDTO;
import com.employeemanagement.employeemanagement.service.CollaboratorService;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorController {
	
	@Autowired
	private CollaboratorService collaboratorService;
	
	//GetAll
	@GetMapping(value = "/getAll")
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<CollaboratorDTO> collaboratorListDTO = getCollaboratorService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(collaboratorListDTO);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
		}
	}
	
	//Inserir apenas um colaborador
	@PostMapping(value = "/save")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody CollaboratorDTO collaboratorDTO) {
		try {
			getCollaboratorService().save(collaboratorDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Collaborator inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}
	
	@PutMapping(value = "/update")
	public @ResponseBody ResponseEntity<String> update(@RequestBody CollaboratorDTO collaboratorDTO) {
		try {
			String message = getCollaboratorService().update(collaboratorDTO);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying to update collaborator. Error message: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
//	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
//		try {
//			getCollaboratorService().delete(id);
//			return ResponseEntity.status(HttpStatus.OK).body("Pokemon deleted succesfully");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error, message: " + e.getMessage());
//		}	
//	}
	
	private CollaboratorService getCollaboratorService() {
		return collaboratorService;
	}

}
