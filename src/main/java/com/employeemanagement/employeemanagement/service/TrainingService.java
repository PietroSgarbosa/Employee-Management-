package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.TrainingDTOMissingException;
import com.employeemanagement.employeemanagement.exception.TrainingNameMissingException;
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
	
	public void save(TrainingDTO trainingDTO) {
		if(trainingDTO != null) {
			if(trainingDTO.getName() == null) {
				throw new TrainingNameMissingException();
			} else {
				Training trainingEntity = getTrainingMapper().convertToEntity(trainingDTO);
				getTrainingRepository().save(trainingEntity);
			}
		} else {
			throw new TrainingDTOMissingException();
		}
	}
	
	public String update(TrainingDTO trainingDTO) {
		Training defaultTraining = getById(trainingDTO.getId());
		String responseMessage = "Training of ID " + trainingDTO.getId() + " not found";

		if(defaultTraining != null) {
			defaultTraining.setName(trainingDTO.getName());
			defaultTraining.setDescription(trainingDTO.getDescription());
			save(TrainingDTO.convertToDTO(defaultTraining));
			responseMessage = "Training of ID " + trainingDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	
	public String delete(Long id) {
		Training training = getById(id);
		
		if(training == null) {
			return "This Training ID " + id + " doesn't exist";
		} else {
			getTrainingRepository().deleteById(id);
			return "Training of ID " + id + " removed!";
		}
	}
	
	
	
	public TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	public TrainingMapper getTrainingMapper() {
		return trainingMapper;
	}


}
