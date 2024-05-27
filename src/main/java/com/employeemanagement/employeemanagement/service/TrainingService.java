package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.TrainingMapper;

@Service
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private TrainingMapper trainingMapper;
	
	public Training getById(Long id) {
		return getTrainingRepository().findById(id).orElse(null);
	}

	public List<TrainingDTO> getAll(){
		List<Training> trainingList = getTrainingRepository().findAll();
		List<TrainingDTO> trainingListDTO = trainingList.stream().map(training -> TrainingDTO.convertToDTO(training)).toList();
		
		if(!trainingListDTO.isEmpty()) {
			return trainingListDTO;
		} else {
			return null;
		}
	}
	
	public String update(TrainingDTO trainingDTO) {
		Training defaultTraining = getById(trainingDTO.getID());
		String responseMessage = "Collaborator of ID " + trainingDTO.getID() + " not found";

		if(defaultTraining != null) {
			defaultTraining.setTitle(trainingDTO.getTitle());
			defaultTraining.setDescription(trainingDTO.getDescription());
			defaultTraining.setDuration(trainingDTO.getDuration());
			create(TrainingDTO.convertToDTO(defaultTraining));
			responseMessage = "Training of ID " + trainingDTO.getID() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	
	public void create(TrainingDTO trainingDTO) {
		if(trainingDTO != null) {
			if(trainingDTO.getTitle() == null && trainingDTO.getDescription() == null) {
				throw new IllegalArgumentException("The name and description cannot be null");
			} else {
				Training trainingEntity = getTrainingMapper().covertToEntity(trainingDTO);
				getTrainingRepository().save(trainingEntity);
			}
		} else {
			throw new IllegalArgumentException("The trainingDTO object cannot be null");
		}
	}
	
	public String delete(Long id) {
		Training training = getById(id);
		
		if(training == null) {
			return "This training ID " + id + " doesn't exist";
		} else {
			getTrainingRepository().deleteById(id);
			return "Training of ID " + id + " removed!";
		}
	}
	
	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	private TrainingMapper getTrainingMapper() {
		return trainingMapper;
	}
	
	
}
