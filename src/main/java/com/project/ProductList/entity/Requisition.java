package com.project.ProductList.entity;



import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ProductList.payloads.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requisitionId;
	 
	@Range(min = 1,message="requisition quantity can't be zero or negative")
	 private int requisitionQuantity;
	 
	private int userid;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonIgnoreProperties("requisitions")
	private Product product;
	
	@Transient
	private UserDTO user;

	public void setUser(UserDTO user2) {
		this.user = user2;		
	}
	
	
}
