package com.project.ProductList.payloads;

public class ApiResponse {
	private String message;
	private Boolean isSuccessful;
	
	public ApiResponse(String message, Boolean isSuccessful) {
		super();
		this.message = message;
		this.isSuccessful = isSuccessful;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(Boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", isSuccessful=" + isSuccessful + "]";
	}
	


}
