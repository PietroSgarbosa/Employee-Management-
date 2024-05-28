package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.EmployeeDTOMissingException;
import com.employeemanagement.employeemanagement.exception.EmployeeNameMissingException;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
import com.employeemanagement.employeemanagement.repository.StatusRepository;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;



@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private EmployeeTrainingRepository employeetrainingRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;	
	
	
	public Employee getById(Long id) {
		return getEmployeeRepository().findById(id).orElse(null);
	}
	
	
	
	//Método para buscar uma lista de todos os colaboradores
	public List<EmployeeDTO> getAll(){
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTO> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTO.convertToDTO(employee)).toList();
		
		if(!employeeListDTO.isEmpty()) {
			return employeeListDTO;
		} else {
			return null;
		}
	}
	
	

	
	
	
	//Delete por ID
	public String delete(Long id) {
		Employee employee = getById(id);
		
		if(employee == null) {
			return "This employee ID " + id + " doesn't exist";
		} else {
			getEmployeeRepository().deleteById(id);
			return "Employee of ID " + id + " removed!";
		}
	}
 	
	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
	
	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}
	
	private EmployeeTrainingRepository getEmployeeTraningRepository() {
		return employeetrainingRepository;
	}
	
	private StatusRepository getStatusRepository() {
		return statusRepository;
	}
	
	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}	

}
