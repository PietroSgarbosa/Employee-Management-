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
	private EmployeeMapper employeeMapper;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private EmployeeTrainingRepository employeeTrainingRepository;

	@Autowired
	private StatusRepository statusRepository;

	public EmployeeDTO getById(Long id) {
		EmployeeDTO employeeDTO = EmployeeDTO.convertToDTO(getEmployeeRepository().findById(id).orElse(null));
		return employeeDTO;
	}

	public List<EmployeeDTO> getAll() {
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTO> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTO.convertToDTO(employee))
				.toList();

		if (!employeeListDTO.isEmpty()) {
			return employeeListDTO;
		} else {
			return null;
		}

	}

	public void create(EmployeeDTO employeeDTO) {
		if (employeeDTO != null) {
			if (employeeDTO.getFirstName() == null) {
				throw new EmployeeNameMissingException();
			} else {
				Employee employeeEntity = getEmployeeMapper().covertToEntity(employeeDTO);
				getEmployeeRepository().save(employeeEntity);

				if (employeeDTO.getTrainingsId() != null) {
					for (Long trainingId : employeeDTO.getTrainingsId()) {
						EmployeeTraining relationship = new EmployeeTraining();
						Training training = getTrainingRepository().getById(trainingId);
						relationship.setTraining(training);
						Status status = getStatusRepository().getById((long) 1);
						relationship.setStatus(status);
						relationship.setEmployee(employeeEntity);

						employeeTrainingRepository.save(relationship);
					}
				}
			}
		} else {
			throw new EmployeeDTOMissingException();
		}
	}

	public String update(EmployeeDTO employeeDTO) {
		Employee defaultEmployee = getEmployeeRepository().findById(employeeDTO.getId()).orElseThrow();
		String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";

		if (defaultEmployee != null) {
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

	public void delete(Long id) {

		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		List<EmployeeTraining> listTraining = getEmployeeTrainingRepository().getByEmployee(employee);
		for (EmployeeTraining deleteTraining : listTraining) {
			getEmployeeTrainingRepository().delete(deleteTraining);

		}

		getEmployeeRepository().deleteById(id);

	}

	public String updateStartTraining(Long idEmployee, Long idTraining) {

		Employee employee = getEmployeeRepository().findById(idEmployee).orElse(null);
		Training training = getTrainingRepository().findById(idTraining).orElse(null);
		String finalizado = "Iniciado";
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findByTrainingAndEmployee(training,
				employee);
		Status status = getStatusRepository().findByDescription(finalizado);
		if (employee.getId() != null && training.getId() != null) {
			employeeTraining.setStatus(status);
			getEmployeeTrainingRepository().save(employeeTraining);

			return "Relationship uptaded";
		} else {
			return "Employee or Training not found";

		}
	}

	public String finishStartTraining(Long idEmployee, Long idTraining) {

		Employee employee = getEmployeeRepository().findById(idEmployee).orElse(null);
		Training training = getTrainingRepository().findById(idTraining).orElse(null);
		String finalizado = "Finalizado";
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findByTrainingAndEmployee(training,
				employee);
		Status status = getStatusRepository().findByDescription(finalizado);
		if (employee.getId() != null && training.getId() != null) {
			employeeTraining.setStatus(status);
			getEmployeeTrainingRepository().save(employeeTraining);

			return "Relationship uptaded";
		} else {
			return "Employee or Training not found";

		}
	}

	private StatusRepository getStatusRepository() {
		return statusRepository;
	}

	private EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeeTrainingRepository;
	}

	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}

}