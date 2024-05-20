package com.employeemanagement.employeemanagement.exception;

public class EmployeeNameMissingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeNameMissingException() {
		super("NAME CANNOT BE NULL");
	}
	
	public EmployeeNameMissingException(String errorMessage) {
		super(errorMessage);
	}

}
