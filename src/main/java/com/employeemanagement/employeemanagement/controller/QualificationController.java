package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.dto.QualificationDTO;
import com.employeemanagement.employeemanagement.service.QualificationService;
import com.employeemanagement.employeemanagement.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

	@Autowired
	private QualificationService qualificationService;

	@Operation(
			summary = "Get all employees and their information about qualifications using filtered search", 
			description = "Returns a list of Qualification Data or an empty list")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = {
					@Content(schema = @Schema(implementation = EmployeeDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal error on server/API") })
	@JsonView(Views.Basic.class)
	@PostMapping()
	public @ResponseBody ResponseEntity<?> getAll(@RequestBody EmployeeFilterDTO employeeFilterDTO) {
		try {
			List<QualificationDTO> qualificationListDTO = getQualificationService().getAll(employeeFilterDTO);
			return ResponseEntity.status(HttpStatus.OK).body(qualificationListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}
	
    @Operation(
    		summary = "Get qualification by employee's ID", 
    		description = "Returns QualifitcationDTO or null")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = { @Content(schema = @Schema(implementation = QualificationDTO.class), mediaType ="application/json")}),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
    @JsonView(Views.Basic.class)
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			QualificationDTO qualificationDTO = getQualificationService().getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(qualificationDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private QualificationService getQualificationService() {
		return qualificationService;
	}

}
