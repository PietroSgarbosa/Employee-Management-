package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
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
	
	public Employee getById(Long id) {
        return getEmployeeRepository().findById(id).orElse(null);
    }
	
	
	public void create(EmployeeDTO employeeDTO) {
		Employee employee = getEmployeeMapper().covertToEntity(employeeDTO);
		getEmployeeRepository().save(employee);
		for(Long list: employeeDTO.getTrainingList()) {
			Status status = new Status((long) 1);
			Training training = new Training(list);
			Employee employe = new Employee(employee.getId());
			TrainingEmployeeKey trainingEmployeeKey = new TrainingEmployeeKey(employee.getId(), list);
			TrainingEmployee trainingEmployee = new TrainingEmployee(trainingEmployeeKey, employe, training, status);
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
