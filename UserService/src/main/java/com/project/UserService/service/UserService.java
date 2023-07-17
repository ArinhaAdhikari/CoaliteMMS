package com.project.UserService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.project.UserService.entity.User;
import com.project.UserService.exceptions.ResourceNotFoundException;
import com.project.UserService.payloads.RequisitionDto;
import com.project.UserService.payloads.UserLogin;
import com.project.UserService.repository.UserRepository;
@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	//System.out.println("This is %s","arinha)
	//logger.info("This is {}","arinha")


	
	@Override
	public User getUserById(int id) {
		List<RequisitionDto> requisitions = new ArrayList<>();
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "userId", id));
		List<Object> forObject = this.restTemplate.getForObject("http://PRODUCT-SERVICE/products/requisitions/user/"+id, List.class);
		if(forObject==null)
		{
			user.setRequisitionDtos(null);
		}
		else {
		forObject.forEach((obj)->{
			System.out.println(obj);
			RequisitionDto reqDto = this.modelMapper.map(obj, RequisitionDto.class);
			System.out.println(reqDto);
			requisitions.add(reqDto);
		});
		}
		
		user.setRequisitionDtos(requisitions);
		logger.info("The response is {}",user);
		return user;
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "userId", id));
		userRepository.delete(user);;
		
	}
	
	public User getUserByIdService(int userId) {
		User user = this.userRepository.findById(userId).orElse(null);
		return user;
	}
	

	@Override
	public User login(UserLogin userlogin) {
		// TODO Auto-generated method stub
		final var email = userlogin.getEmail();
		final var password = userlogin.getPassword();
		final var user = userRepository.searchByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("user", "user_email", email);
		}
		if (user == null || !user.getPassword().equals(password)) {
			throw new ResourceNotFoundException("password", "user_email", email);
		}
		return user;
	
	}

	

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		final var user = userRepository.searchByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("user", "user_email", email);
		}
		return user;
	
	}

	@Override
	public String register(User user) {
		final var u = userRepository.searchByEmail(user.getEmail());
		var res = "";
		if (u == null) {
			user.setRole(0);
			userRepository.save(user);
			res = "successfully registered";
		} else {
			res = "Email exist already. Try login!";

		}
		return res;
	}


	

}
