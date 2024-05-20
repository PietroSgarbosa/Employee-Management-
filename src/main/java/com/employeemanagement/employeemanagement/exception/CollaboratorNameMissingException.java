package com.employeemanagement.employeemanagement.exception;

public class CollaboratorNameMissingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CollaboratorNameMissingException() {
		super("NAME CANNOT BE NULL");
	}
	
	public CollaboratorNameMissingException(String errorMessage) {
		super(errorMessage);
	}

}
