package com.project.UserService.entity;

import java.util.ArrayList;
import java.util.List;

import com.project.UserService.payloads.RequisitionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@AllArgsConstructor

@Setter
@Getter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;
	
	 @NotBlank(message = "Name must not be empty")
	 @Size(min=10,max=50,message = "Name must be between 10 and 50 words")
	 private String name;
	 
	 @Column(unique = true)
		@NotBlank(message = "Email must not be blank")
		@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!")
	 private String email;
	 
	 @NotBlank(message = "Password must not be empty")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	 private String password;
	 
		private int role;

		@Override
		public String toString() {
			return "User [id=" + UserId + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
					 + "]";
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(int UserId,
				@NotBlank(message = "User name must not be blank") @Size(min = 3, max = 30, message = "User name must be between 3 and 30 letters") String name,
				@NotBlank(message = "email must not be blank") @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email") String email,
				@NotBlank(message = "Password must not be empty") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be minimum 8 characters long, must contain one uppercase character,one lowercase,one number and one special character") String password) {
			super();
			this.UserId = UserId;
			this.name = name;
			this.email = email;
			this.password = password;
		}

		public User(int UserId,
				@NotBlank(message = "User name must not be blank") @Size(min = 3, max = 30, message = "User name must be between 3 and 30 letters") String name,
				@NotBlank(message = "email must not be blank") @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email") String email,
				@NotBlank(message = "Password must not be empty") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be minimum 8 characters long, must contain one uppercase character,one lowercase,one number and one special character") String password,
				int role) {
			super();
			this.UserId = UserId;
			this.name = name;
			this.email = email;
			this.password = password;
			this.role = role;
		}

		
		public int getId() {
			return UserId;
		}

		public void setId(int id) {
			this.UserId = UserId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getRole() {
			return role;
		}

		public void setRole(int role) {
			this.role = role;
		}


	@Transient
	 private List<RequisitionDto> requisitionDtos;
	  

}
