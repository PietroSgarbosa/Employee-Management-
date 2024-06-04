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
						Training training = getTrainingRepository().findById(trainingId).orElseThrow();
						relationship.setTraining(training);
						Status status = getStatusRepository().findById((long) 1).orElseThrow();
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
		Employee defaultEmployee = getEmployeeRepository().findById(employeeDTO.getId())
				.orElseThrow(() -> new RuntimeException("Collaborator of ID " + employeeDTO.getId() + " not found"));

		defaultEmployee.setFirstName(
				employeeDTO.getFirstName() != null && !employeeDTO.getFirstName().isEmpty() ? employeeDTO.getFirstName()
						: defaultEmployee.getFirstName());
		defaultEmployee.setMiddleName(employeeDTO.getMiddleName() != null && !employeeDTO.getMiddleName().isEmpty()
				? employeeDTO.getMiddleName()
				: defaultEmployee.getMiddleName());
		defaultEmployee.setLastName(
				employeeDTO.getLastName() != null && !employeeDTO.getLastName().isEmpty() ? employeeDTO.getLastName()
						: defaultEmployee.getLastName());
		defaultEmployee.setCpf(employeeDTO.getCpf() != null && !employeeDTO.getCpf().isEmpty() ? employeeDTO.getCpf()
				: defaultEmployee.getCpf());
		defaultEmployee.setCategory(
				employeeDTO.getCategory() != null && !employeeDTO.getCategory().isEmpty() ? employeeDTO.getCategory()
						: defaultEmployee.getCategory());

		getEmployeeRepository().save(defaultEmployee);

		if (employeeDTO.getTrainingsId() != null) {
			for (Long trainingId : employeeDTO.getTrainingsId()) {
				Training training = getTrainingRepository().findById(trainingId)
						.orElseThrow(() -> new RuntimeException("Training of ID " + trainingId + " not found"));

				EmployeeTraining relationship = new EmployeeTraining();
				relationship.setTraining(training);
				Status status = getStatusRepository().findById(1L)
						.orElseThrow(() -> new RuntimeException("Status of ID 1 not found"));
				relationship.setStatus(status);
				relationship.setEmployee(defaultEmployee);

				employeeTrainingRepository.save(relationship);
			}
		}

		return "Employee of ID " + employeeDTO.getId() + " updated successfully!";
	}

	public String inProgress(Long iDEmployee, Long iDTraining) {
		Employee defaultEmployee = getEmployeeRepository().findById(iDEmployee).orElse(null);
		if (defaultEmployee != null) {
			for (EmployeeTraining training : defaultEmployee.getTrainings()) {
				if (training.getTraining().getId() == iDTraining && training.getStatus().getId() < 2L) {
					employeeTrainingRepository.delete(training);

					EmployeeTraining relationship = new EmployeeTraining();
					Training trainingFound = getTrainingRepository().findById(iDTraining).orElseThrow();
					relationship.setTraining(trainingFound);
					Status status = getStatusRepository().findById((long) 2).orElseThrow();
					relationship.setStatus(status);
					relationship.setEmployee(defaultEmployee);

					employeeTrainingRepository.save(relationship);

				}
			}
			return "Training status changed successfully";
		}

		return "Error when changing status";
	}

	public String concluded(Long iDEmployee, Long iDTraining) {
		Employee defaultEmployee = getEmployeeRepository().findById(iDEmployee).orElse(null);
		if (defaultEmployee != null) {
			for (EmployeeTraining training : defaultEmployee.getTrainings()) {
				if (training.getTraining().getId() == iDTraining && training.getStatus().getId() < 3L
						&& training.getStatus().getId() > 1L) {
					employeeTrainingRepository.delete(training);

					EmployeeTraining relationship = new EmployeeTraining();
					Training trainingFound = getTrainingRepository().findById(iDTraining).orElseThrow();
					relationship.setTraining(trainingFound);
					Status status = getStatusRepository().findById((long) 3).orElseThrow();
					relationship.setStatus(status);
					relationship.setEmployee(defaultEmployee);

					employeeTrainingRepository.save(relationship);
				}

			}
			return "Training status changed successfully";
		}

		return "Error when changing status";
	}

	public void delete(Long id) {

		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		List<EmployeeTraining> listTraining = getEmployeeTrainingRepository().getByEmployee(employee);
		for (EmployeeTraining deleteTraining : listTraining) {
			getEmployeeTrainingRepository().delete(deleteTraining);

		}

		getEmployeeRepository().deleteById(id);

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