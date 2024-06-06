package com.employeemanagement.employeemanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingKey;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.EmployeeDTOMissingException;
import com.employeemanagement.employeemanagement.exception.EmployeeNameMissingException;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
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
	private EmployeeTrainingRepository employeeTrainingRepository;
	
	@Autowired
	private TrainingService trainingService;
	
	
	@Autowired
	private CategoryService categoryService;

	public EmployeeDTO getById(Long id) {
		EmployeeDTO employeeDTO = EmployeeDTO.convertToDTO(getEmployeeRepository().findById(id).orElse(null));
		return employeeDTO;
	}

	public List<EmployeeDTO> getAll(EmployeeFilterDTO employeeFilterDTO){
		Specification<Employee> specification = EmployeeSpecification.withAtributes(employeeFilterDTO);
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTO> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTO.convertToDTO(employee))
				.toList();
		return employeeListDTO;
	}

	public void create(EmployeeDTO employeeDTO) {
		if (employeeDTO != null) {
			if (employeeDTO.getFirstName() == null) {
				throw new EmployeeNameMissingException();
				
			} else {
				
				Employee employeeEntity = getEmployeeMapper().covertToEntity(employeeDTO);
				Category category = getCategoryService().finById(employeeDTO.getCategoryId());	
				employeeEntity.setCategory(category);
				getEmployeeRepository().save(employeeEntity);
				if (employeeDTO.getTrainings() != null) {
					for (Long trainingId : employeeDTO.getListId()) {
						Status status = new Status((long) 1);
						Training training = new Training(trainingId);						
						Employee employe = new Employee(employeeEntity.getId());
						EmployeeTrainingKey employeeTrainingKey = new EmployeeTrainingKey(employeeEntity.getId(), trainingId);
						EmployeeTraining employeeTraining = new EmployeeTraining(employeeTrainingKey, employe, training, status);						
						employeeTrainingRepository.save(employeeTraining);					}
				}
			}
		} else {
			throw new EmployeeDTOMissingException();
		}
	}

	public String update(EmployeeDTO employeeDTO) {
		Employee employee = getEmployeeRepository().findById(employeeDTO.getId()).orElseThrow();
		String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";

		if (employee != null) {
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setMiddleName(employeeDTO.getMiddleName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setCpf(employeeDTO.getCpf());
			Category category = getCategoryService().finById(employeeDTO.getCategoryId());	
			employee.setCategory(category);
			
			//List<Long> listId = employeeDTO.getListId();
			
//			for (Long id : listId) {				
//				Training training = getTrainingService().getById(id);
//				if (training != null) {
//					Status status = new Status((long) 1);					
//					EmployeeTrainingKey employeeTrainingKey = new EmployeeTrainingKey(employee.getId(), training.getId());					
//					EmployeeTraining employeeTraining = new EmployeeTraining(employeeTrainingKey, employee, training, status);
//					getEmployeeTrainingRepository().save(employeeTraining);					
//				}				
//			}			
			getEmployeeRepository().save(employee);
			responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	

	public void delete(Long id) {
		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		List<EmployeeTraining> listTraining = getEmployeeTrainingRepository().getByEmployee(employee);
		for (EmployeeTraining deleteTraining : listTraining) {
			getEmployeeTrainingRepository().delete(deleteTraining);

		}
		getEmployeeRepository().deleteById(id);

	}
  
	public void finishTraining(Long idEmployee, Long idTraining) {
		EmployeeTrainingKey employeeTrainingKey = new EmployeeTrainingKey(idEmployee, idTraining);
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findById(employeeTrainingKey)
		.orElseThrow(() -> new EntityNotFoundException("The user with id: " + idEmployee + ", its not registred on Training of id: " + idTraining));
		Status statusAtual = employeeTraining.getStatus();
		if(statusAtual.getId() == 3) {
			throw new IllegalArgumentException("You cannot finish a training that is already over");
		}
		Status status = new Status((long) 3);
		employeeTraining.setStatus(status);
		getEmployeeTrainingRepository().save(employeeTraining);
	}
	
	public void startTraining(Long idEmployee, Long idTraining) {
		EmployeeTrainingKey employeeTrainingKey = new EmployeeTrainingKey(idEmployee, idTraining);
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findById(employeeTrainingKey)
		.orElseThrow(() -> new EntityNotFoundException("The user with id: " + idEmployee + ", its not registred on Training of id: " + idTraining));
		Status statusAtual = employeeTraining.getStatus();
		if(statusAtual.getId() == 2) {
			throw new IllegalArgumentException("You cannot begin a training that is already in progress");
		}
		Status status = new Status((long) 2);
		employeeTraining.setStatus(status);
		getEmployeeTrainingRepository().save(employeeTraining);
	}

	private EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeeTrainingRepository;
	}
	
	private CategoryService getCategoryService() {
		return categoryService;
	}
	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
		
	private TrainingService getTrainingService() {
		return trainingService;
	}

	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}	

}