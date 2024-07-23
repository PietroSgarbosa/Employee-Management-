package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.OccupationalHealthCertificateDTO;
import com.employeemanagement.employeemanagement.dto.OccupationalHealthCertificateTypeDTO;
import com.employeemanagement.employeemanagement.service.OccupationalHealthCertificateService;
import com.employeemanagement.employeemanagement.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/healthCertificates")
public class OccupationalHealthCertificateController {
	
	@Autowired
	private OccupationalHealthCertificateService healthService;
	
	@GetMapping()
	@JsonView(Views.Basic.class)
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<OccupationalHealthCertificateDTO> healthCertificateList = getHealthService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(healthCertificateList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}
	
	@GetMapping(value = "/{id}") 
	@JsonView(Views.Basic.class)
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			OccupationalHealthCertificateDTO healthCertificateDTO = getHealthService().getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(healthCertificateDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getTypeList") 
	@JsonView(Views.Basic.class)
	public @ResponseBody ResponseEntity<?> getTypeList() {
		try {
			List<OccupationalHealthCertificateTypeDTO> typeListDTO = getHealthService().getTypeList();
			return ResponseEntity.status(HttpStatus.OK).body(typeListDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public @ResponseBody ResponseEntity<?> create(@RequestBody OccupationalHealthCertificateDTO healthCertificateDTO) {
		try {
			getHealthService().create(healthCertificateDTO);
			return ResponseEntity.status(HttpStatus.OK).body("New health certificate registered successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}
	
	@PutMapping()
	public @ResponseBody ResponseEntity<String> update(@RequestBody OccupationalHealthCertificateDTO healthCertificateDTO) {
		try {
			getHealthService().update(healthCertificateDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Health certificate updated successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update employee. Error message: " + e.getMessage());
		}
	}
	
	@DeleteMapping()
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			String message = getHealthService().delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal error, message: " + e.getMessage());
		}
	}

	
	private OccupationalHealthCertificateService getHealthService() {
		return healthService;
	}

}

