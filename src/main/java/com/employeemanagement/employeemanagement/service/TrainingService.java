package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.TrainingMapper;

@Service
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private TrainingMapper trainingMapper;

	@Autowired
	private EmployeeTrainingRepository employeeTrainingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public TrainingDTO getById(Long id) {
		Training training = getTrainingRepository().findById(id).orElse(null);
		TrainingDTO trainingDTO = TrainingDTO.convertToDTO(training);
		if(training.getCategory() != null) {
			trainingDTO.setCategoryDTO(CategoryDTO.convertToDTO(training.getCategory()));
		}
		return trainingDTO;
	}

	public List<TrainingDTO> getAll() {
		List<Training> trainingList = getTrainingRepository().findAllByOrderByTitleAsc();
		List<TrainingDTO> trainingListDTO = trainingList.stream().map(training -> TrainingDTO.convertToDTO(training))
				.toList();

		if (!trainingListDTO.isEmpty()) {
			for (Training training : trainingList) {
				if(training.getCategory() != null) {
					CategoryDTO categoryDTO = CategoryDTO.convertToDTO(training.getCategory());
					trainingListDTO.get(trainingList.indexOf(training)).setCategoryDTO(categoryDTO);
				}
			}
			return trainingListDTO;
		} else {
			return null;
		}
	}

	public String update(TrainingDTO trainingDTO) {
		Training defaultTraining = getTrainingRepository().findById(trainingDTO.getId()).orElse(null);
		String responseMessage = "Training of ID " + trainingDTO.getId() + " not found";

		if (defaultTraining != null) {
			defaultTraining.setTitle(trainingDTO.getTitle());
			defaultTraining.setDescription(trainingDTO.getDescription());
			create(TrainingDTO.convertToDTO(defaultTraining));
			responseMessage = "Training of ID " + trainingDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}

	public void create(TrainingDTO trainingDTO) {
		if (trainingDTO != null) {
			if (trainingDTO.getTitle() == null && trainingDTO.getDescription() == null) {
				throw new IllegalArgumentException("The name and description cannot be null");
			} else {
				Training trainingEntity = getTrainingMapper().covertToEntity(trainingDTO);
				getTrainingRepository().save(trainingEntity);
				if(trainingDTO.getCategoryId() != null) {
					Category category = categoryRepository.findById(trainingDTO.getCategoryId()).orElse(null);
					trainingEntity.setCategory(category);
					category.getTrainings().add(trainingEntity);
					categoryRepository.save(category);
					getTrainingRepository().save(trainingEntity);
				}
				
			}
		} else {
			throw new IllegalArgumentException("The trainingDTO object cannot be null");
		}
	}

	public void delete(Long id) {

		Training training = getTrainingRepository().findById(id).orElse(null);
		List<EmployeeTraining> listTraining = getEmployeeTrainingRepository().getBytraining(training);
		for (EmployeeTraining deleteTraining : listTraining) {
			getEmployeeTrainingRepository().delete(deleteTraining);

		}

		getTrainingRepository().deleteById(id);

	}

	private EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeeTrainingRepository;
	}

	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	private TrainingMapper getTrainingMapper() {
		return trainingMapper;
	}

}