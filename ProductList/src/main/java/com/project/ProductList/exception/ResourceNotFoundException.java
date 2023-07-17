package com.project.ProductList.exception;

public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	Integer fieldVal;// if the entered val is int
	String fieldValue;// if the entered val is string
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldVal) {
		super(String.format("%s is not found for %s : %s", resourceName, fieldName, fieldVal));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldVal = fieldVal;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s is not found for %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


}
