package com.project.ProductList.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	 @NotBlank(message = "Product Name must not be empty")
	 @Size(min=5,max=50,message = "Name must be between 5 and 50 words")
	 private String productName;
	 
	 @NotBlank(message = "Product Department must not be empty")
	 @Size(min=3, max=20, message=" please enter 3 to 20 characters only")
	 private String productDept;
	 
	 @NotNull(message = "Quantity must not be empty. Put 0 for non available items")
	 @Range(min = 1,message="Product quantity can't be negative")
	 private int productQuantity;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "product")
	@JsonIgnoreProperties(value = "product")
	private List<Requisition>  requisitions;
}
