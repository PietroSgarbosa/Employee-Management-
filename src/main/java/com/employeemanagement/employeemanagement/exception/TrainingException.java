package com.employeemanagement.employeemanagement.exception;

public class TrainingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrainingException() {
		super("ID NOT FOUND");
	}

	public TrainingException(String error) {
		super(error);
	}

}



