package com.project.UserService.payloads;

public class ProductDto {
	private int productId;
	private String productName;
	private String productDept;
	private int productQuantity;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDept() {
		return productDept;
	}
	public void setProductDept(String productDept) {
		this.productDept = productDept;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(int productId, String productName, String productDept, int productQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDept = productDept;
		this.productQuantity = productQuantity;
	}
	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName=" + productName + ", productDept=" + productDept
				+ ", productQuantity=" + productQuantity + "]";
	}
	
	
}
