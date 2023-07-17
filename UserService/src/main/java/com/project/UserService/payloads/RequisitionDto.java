package com.project.UserService.payloads;

public class RequisitionDto {

	
	private int requisitionId;
	private int requisitionQuantity;
	private String status;
	
	private ProductDto product;

	public int getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(int requisitionId) {
		this.requisitionId = requisitionId;
	}

	public int getRequisitionQuantity() {
		return requisitionQuantity;
	}

	public void setRequisitionQuantity(int requisitionQuantity) {
		this.requisitionQuantity = requisitionQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto productDto) {
		this.product = productDto;
	}

	public RequisitionDto(int requisitionId, int requisitionQuantity, String status, ProductDto productDto) {
		super();
		this.requisitionId = requisitionId;
		this.requisitionQuantity = requisitionQuantity;
		this.status = status;
		this.product = productDto;
	}

	public RequisitionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RequisitionDto [requisitionId=" + requisitionId + ", requisitionQuantity=" + requisitionQuantity
				+ ", status=" + status + ", product=" + product + "]";
	}
	
	
	
	
}
