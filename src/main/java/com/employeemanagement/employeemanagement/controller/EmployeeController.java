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
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/getAll")
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<EmployeeDTO> employeeListDTO = getEmployeeService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(employeeListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}

	@GetMapping(value = "/getById/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			EmployeeDTO employeeDTO = getEmployeeService().getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/create")
	public @ResponseBody ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
		try {
			getEmployeeService().create(employeeDTO);
			return ResponseEntity.status(HttpStatus.OK).body("employee inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public @ResponseBody ResponseEntity<String> update(@RequestBody EmployeeDTO employeeDTO) {
		try {
			String message = getEmployeeService().update(employeeDTO);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update employee. Error message: " + e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete")
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			getEmployeeService().delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("employee deleted succesfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal error, message: " + e.getMessage());
		}
	}
	
	@PostMapping(value = "/filtrar")
	public @ResponseBody ResponseEntity<List<EmployeeFilterDTO>> filtrar(@RequestBody EmployeeFilterDTO filter) {
		try {
			List<EmployeeFilterDTO> employeesFilterDTO = getEmployeeService().findyFilter(filter);
			return ResponseEntity.ok(employeesFilterDTO);
		} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}
}
	
	
	

	private EmployeeService getEmployeeService() {
		return employeeService;
	}

}