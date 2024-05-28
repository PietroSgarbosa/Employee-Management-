package com.employeemanagement.employeemanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeWithTrainingsDTO;
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
	
	
//	public Employee getById(Long id) {
//		return getEmployeeRepository().findById(id).orElse(null);
//	}
	
	public Employee getById(Long id) {
		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		List<EmployeeTraining> listRelationship = employee.getTrainings();
		EmployeeWithTrainingsDTO employeeWithTrainings = new EmployeeWithTrainingsDTO();
		
		return employee;
		}
	
	public EmployeeTraining getByIdTraining(Long id) {	
		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		List<EmployeeTraining> listTraining = employee.getTrainings();
		//EmployeeDTO employeeTrai = new EmployeeDTO();
		
		return (EmployeeTraining) listTraining;
//		Optional<Employee> optional = getEmployeeRepository().findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			List<EmployeeTraining> trainings = employee.getTrainings();
//			return new Employee(employee, trainings);			
//		} else {
//			throw new EmployeeDTOMissingException();
//		}
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
	
	//Método para inserir um colaborador
	public void create(EmployeeDTO employeeDTO) {
		if(employeeDTO != null) {
			if(employeeDTO.getFirstName() == null) {
				throw new EmployeeNameMissingException();
			} else {
				Employee employeeEntity = getEmployeeMapper().covertToEntity(employeeDTO);
				getEmployeeRepository().save(employeeEntity);
				for (EmployeeTraining relationship : employeeEntity.getTrainings()) {
					relationship.setEmployeeId(employeeEntity);
					Training training = getTrainingRepository().findById(relationship.getTrainingId().getId()).orElse(null);
					relationship.setTrainingId(training);
					Status status = getStatusRepository().findById(relationship.getStatus().getId()).orElse(null);
					relationship.setStatus(status);
					getEmployeeTraningRepository().save(relationship);				
				}
			}
		} else {
			throw new EmployeeDTOMissingException();
		}
	}	


//	//Método para inserir um colaborador
//		public void createTraining(EmployeeDTO employeeDTO) {
//			if(employeeDTO != null) {
//				if(employeeDTO.getFirstName() == null) {
//					throw new EmployeeNameMissingException();
//				} else {
//					Employee employeeEntity = getEmployeeMapper().covertToEntity(employeeDTO);
//					getEmployeeRepository().save(employeeEntity);
//					for (EmployeeTraining relationship : employeeEntity.getTrainings()) {
//					relationship.setEmployeeId(employeeEntity);
//					Training training = relationship.getTrainingId();
//						if (training.getId() == null) {
//							getEmployeeRepository().save(training);							
//						}
//					
//					}
//				}
//			} else {
//				throw new EmployeeDTOMissingException();
//			}
//		}
	
	//Atualizando por ID
	public String update(EmployeeDTO employeeDTO) {
		Employee defaultEmployee = getById(employeeDTO.getId());
		String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";

		if(defaultEmployee != null) {
			defaultEmployee.setFirstName(employeeDTO.getFirstName());
			defaultEmployee.setMiddleName(employeeDTO.getMiddleName());
			defaultEmployee.setLastName(employeeDTO.getLastName());
			defaultEmployee.setCpf(employeeDTO.getCpf());
			defaultEmployee.setCategory(employeeDTO.getCategory());
			create(EmployeeDTO.convertToDTO(defaultEmployee));
			responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
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
