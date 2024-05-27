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

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.service.TrainingService;

@RestController
@RequestMapping("/trainings")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingService;
	
	@GetMapping(value = "/getById/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Training entity = getTrainingService().getById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(TrainingDTO.convertToDTO(entity));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getAll")
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<TrainingDTO> trainingListDTO = getTrainingService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(trainingListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}
	
	@PostMapping(value = "/create")
	public @ResponseBody ResponseEntity<?> create(@RequestBody TrainingDTO trainingDTO) {
		try {
			getTrainingService().create(trainingDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Training inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}
	
	@PutMapping(value = "/update")
	public @ResponseBody ResponseEntity<String> update(@RequestBody TrainingDTO trainingDTO) {
		try {
			String message = getTrainingService().update(trainingDTO);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update training. Error message: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			getTrainingService().delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Training deleted succesfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal error, message: " + e.getMessage());
		}
	}

	private TrainingService getTrainingService() {
		return trainingService;
	} 
	
}
