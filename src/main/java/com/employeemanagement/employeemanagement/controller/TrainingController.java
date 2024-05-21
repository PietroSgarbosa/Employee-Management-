package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.TrainingException;
import com.employeemanagement.employeemanagement.service.TrainingService;



@RestController
@RequestMapping("/training")
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
	    try {
	        Training training = trainingService.getById(id);
	        return ResponseEntity.ok().body(training);
	    } catch (TrainingException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> create(@RequestBody Training training) {
	    try {
	        trainingService.create(training);
	        return ResponseEntity.ok().body("Training Inserted!");
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("Internal Error: " + e.getMessage());
	    }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    try {
	        trainingService.delete(id);
	        return ResponseEntity.ok().body("Training deleted successfully!");
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Training Not Found");
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("Internal Error: " + e.getMessage());
	    }
	}
	
	@PutMapping("/uptade/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Training trainingCurrent) {
	    try {
	        Training training = trainingService.update(id, trainingCurrent);

	        if (training != null) {
	            return ResponseEntity.ok().body("Update training!");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Training not found!");
	        }
	    } catch (TrainingException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("Internal Error: " + e.getMessage());
	    }
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAll() {
	    try {
	        List<TrainingDTO> trainingListDTO = trainingService.getAll();
	        return ResponseEntity.status(HttpStatus.OK).body(trainingListDTO);
	    } catch(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
	    }
	}


}



