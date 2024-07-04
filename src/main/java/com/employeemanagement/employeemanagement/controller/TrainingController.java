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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/trainings")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingService;
	
    @Operation(
    		summary = "Search training by ID", 
    		description = "Returns a training entity by it exactly ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = { @Content(schema = @Schema(implementation = Training.class), mediaType ="application/json")}),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Training entity = getTrainingService().getById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(TrainingDTO.convertToDTO(entity));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @Operation(
    		summary = "Get all trainings", 
    		description = "Returns a list of TraningDTO or an empty list")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = { @Content(schema = @Schema(implementation = TrainingDTO.class), mediaType ="application/json")}),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@GetMapping()
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<TrainingDTO> trainingListDTO = getTrainingService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(trainingListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}
	
    @Operation(
    		summary = "Submit new training", 
    		description = "Submit new training receiving a TrainingDTO model class")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Training inserted successfully!"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@PostMapping()
	public @ResponseBody ResponseEntity<?> create(@RequestBody TrainingDTO trainingDTO) {
		try {
			getTrainingService().create(trainingDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Training inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}
	
    @Operation(
    		summary = "Update a training", 
    		description = "Update existing training by changing its attributes")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Training updated successfully!"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@PutMapping()
	public @ResponseBody ResponseEntity<String> update(@RequestBody TrainingDTO trainingDTO) {
		try {
			String message = getTrainingService().update(trainingDTO);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update training. Error message: " + e.getMessage());
		}
	}
	
    @Operation(
    		summary = "Delete training", 
    		description = "Delete an existing traning by sending the right ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Training deleted successfully!"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@DeleteMapping()
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
