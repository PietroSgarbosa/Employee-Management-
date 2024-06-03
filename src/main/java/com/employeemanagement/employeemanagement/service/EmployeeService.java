package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeKey;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.TrainingEmployeeRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;
import com.employeemanagement.employeemanagement.utils.EmployeeSpecification;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private TrainingEmployeeRepository trainingEmployeeRepository;
	
	public List<Employee> getAll(){
		return getEmployeeRepository().findAll();
	}
	
	public Employee getById(Long id) {
        return getEmployeeRepository().findById(id).orElse(null);
    }
	
	public List<Employee> findWithFilter(EmployeeFilterDTO employee){
		Specification<Employee> specification = EmployeeSpecification.withAtributes(employee);
		return getEmployeeRepository().findAll(specification);
	}
	
	public void create(EmployeeDTO employeeDTO) {
		Employee employee = getEmployeeMapper().covertToEntity(employeeDTO);
		Category category = new Category(employeeDTO.getCategoryId());
		employee.setCategory(category);
		getEmployeeRepository().save(employee);
		for(Long number: employeeDTO.getTrainingList()) {
			Status status = new Status((long) 1);
			Training training = new Training(number);
			Employee employe = new Employee(employee.getId());
			TrainingEmployeeKey trainingEmployeeKey = new TrainingEmployeeKey(employee.getId(), number);
			TrainingEmployee trainingEmployee = new TrainingEmployee(trainingEmployeeKey, employe, training, status);
			getTrainingEmployeeRepository().save(trainingEmployee);
		}
			
	}
	
	public String update(EmployeeDTO employeeDTO) {
		Employee defaultEmployee = getById(employeeDTO.getId());
		String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";

		if(defaultEmployee != null) {
			defaultEmployee.setFirstName(employeeDTO.getFirstName());
			defaultEmployee.setMiddleName(employeeDTO.getMiddleName());
			defaultEmployee.setLastName(employeeDTO.getLastName());
			defaultEmployee.setCpf(employeeDTO.getCpf());
			defaultEmployee.setCategory(employeeDTO.getCategory());
			getEmployeeRepository().save(defaultEmployee);
			responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	
	public void delete(Long id) {
		Employee employee = getEmployeeRepository().findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe um Employee com o Id: " + id));
		List<TrainingEmployee> listTrainingEmployee = getTrainingEmployeeRepository().getByidEmployee(employee);
		for(TrainingEmployee trainingEmployee: listTrainingEmployee) {
			getTrainingEmployeeRepository().delete(trainingEmployee);		
		}
		getEmployeeRepository().deleteById(id);
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
