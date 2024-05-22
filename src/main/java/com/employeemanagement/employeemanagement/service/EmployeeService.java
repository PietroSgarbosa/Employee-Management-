package com.employeemanagement.employeemanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.TrainingEmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeKey;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.TrainingEmployeeRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private TrainingEmployeeRepository trainingEmployeeRepository;
	
	public List<EmployeeDTO> getAll(){
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTO> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTO.convertToDTO(employee)).toList();
		
		if(!employeeListDTO.isEmpty()) {
			return employeeListDTO;
		} else {
			return null;
		}
	}
	
	public EmployeeDTO getById(Long id) {
		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		TrainingEmployeeKey trainingEmployeeId = new TrainingEmployeeKey();
		trainingEmployeeId.setIdEmployee(employee.getId());
		List<TrainingEmployee> trainingEmployee = getTrainingEmployeeRepository().
				getByNativeQuery(employee.getId());
		List<TrainingEmployeeDTO> trainingEmployeeDTO = trainingEmployee.stream().
				map(trainingEmploye -> TrainingEmployeeDTO.
				convertToDTO(trainingEmploye)).toList();
		EmployeeDTO employeeDTO = EmployeeDTO.convertToDTO(employee);
		employeeDTO.setListTrainingEmployeeDTO(trainingEmployeeDTO);
		return employeeDTO;
	}
	
	public void create(EmployeeDTO employeeDTO) {
		Employee employee = getEmployeeMapper().covertToEntity(employeeDTO);
		getEmployeeRepository().save(employee);
		for(Long list: employeeDTO.getTrainingList()) {
			Status status = new Status();
			Training training = new Training();
			Employee employe = new Employee();
			long id = 1;
			status.setId(id);
			employe.setId(employee.getId());
			training.setID(list);
			TrainingEmployee trainingEmployee = new TrainingEmployee();
			trainingEmployee.setIdEmployee(employe);
			trainingEmployee.setIdStatus(status);
			trainingEmployee.setIdTraining(training);
			TrainingEmployeeKey trainingEmployeeKey = new TrainingEmployeeKey();
			trainingEmployeeKey.setIdEmployee(employee.getId());
			trainingEmployeeKey.setIdTraining(list);
			trainingEmployee.setId(trainingEmployeeKey);
			getTrainingEmployeeRepository().save(trainingEmployee);
		}
			
	}
	
	public void update(EmployeeDTO employeeDTO) {
		Employee defaultEmployee = getEmployeeRepository().findById(employeeDTO.getId()).orElse(null);
		defaultEmployee.setFirstName(employeeDTO.getFirstName());
		defaultEmployee.setMiddleName(employeeDTO.getMiddleName());
		defaultEmployee.setLastName(employeeDTO.getLastName());
		defaultEmployee.setCpf(employeeDTO.getCpf());
		defaultEmployee.setCategory(employeeDTO.getCategory());
		create(EmployeeDTO.convertToDTO(defaultEmployee));
	}
	
	public String delete(Long id) {
	    getEmployeeRepository().deleteById(id);
		return "Employee of ID " + id + " removed!";
	}
 	
	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
	
	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}

	public TrainingEmployeeRepository getTrainingEmployeeRepository() {
		return trainingEmployeeRepository;
	}
}
