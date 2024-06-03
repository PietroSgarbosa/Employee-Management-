package com.employeemanagement.employeemanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingId;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;
import jakarta.transaction.Transactional;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;	
	
	@Autowired
	private EmployeeTrainingRepository employeetrainingRepository;	
	
	@Autowired
	private EmployeeMapper employeeMapper;	
	
	@Autowired
	private TrainingService trainingService;
	
	
	public Employee getById(Long id) {
		return getEmployeeRepository().findById(id).orElse(null);
	}	
	
	
	//Método para buscar uma lista de todos os colaboradores
	public List<Employee> getAll(){
		return getEmployeeRepository().findAll();
		
		
	}
	
	public void create(EmployeeDTO employeeDTO) {	
		
		Employee entity = getEmployeeMapper().covertToEntity(employeeDTO);		
		//entity.setCategory(employeeDTO.getCategory());
		getEmployeeRepository().save(entity);
		
		for (Long list : employeeDTO.getListId()) {			
			Training trainings = new Training(list);			
			
			Employee employee = new Employee(entity.getId());
			
			Status status = new Status((long)1);
					
			EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(entity.getId(), list);
			
			EmployeeTraining employeeTraining = new EmployeeTraining(trainings, employee, status, employeeTrainingId);			
						
			getEmployeeTrainingRepository().save(employeeTraining);
		}
		
	}
	
	public String update(EmployeeDTO employeeDTO) {
		
		Employee employee = getById(employeeDTO.getId());
		String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";
		
		if(employee != null) {
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setMiddleName(employeeDTO.getMiddleName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setCpf(employeeDTO.getCpf());
			employee.setCategory(employeeDTO.getCategory());	
			
			List<Long> list = employeeDTO.getListId();
			
			for (Long id : list ) {
				Training training = getTrainingService().getById(id);
				if (training != null) {
					
					Status status = new Status((long)1);
					
					EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(employee.getId(), training.getId());
					
					EmployeeTraining employeeTraining = new EmployeeTraining(training, employee, status, employeeTrainingId);
					
					getEmployeeTrainingRepository().save(employeeTraining);						
				}		
				
			}	
			getEmployeeRepository().save(employee);
			responseMessage = "Employee of ID " + employeeDTO.getId() + " Update successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
			
	
	
	public void delete(Long employeeId) {
		Employee employee = getEmployeeRepository().findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
		
		List<EmployeeTraining> relations = getEmployeeTrainingRepository().findByEmployee(employee);
		
		getEmployeeTrainingRepository().deleteAll(relations);
						
		getEmployeeRepository().delete(employee);	
	}
		
	
	
 	
	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}	
	
	private EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeetrainingRepository;
	}	
	
	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}	
	
	private TrainingService getTrainingService() {
		return trainingService;
	}
}
