package com.employeemanagement.employeemanagement.exception;

public class TrainingTitleMissingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TrainingTitleMissingException() {
		super("TITLE CANNOT BE NULL");
	}

	public TrainingTitleMissingException(String error) {
		super(error);
	}
	
	
	
}
