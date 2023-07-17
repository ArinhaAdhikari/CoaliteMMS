package com.project.UserService.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserService.entity.User;
import com.project.UserService.payloads.ApiResponse;
import com.project.UserService.payloads.UserLogin;
import com.project.UserService.repository.UserRepository;
import com.project.UserService.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@PostMapping("/user")
	public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody User user)
	{
		logger.info("create user with data = {}",user);
		String res=userService.register(user);
		return new ResponseEntity<>(new ApiResponse(res, true),HttpStatus.ACCEPTED);
	
	}
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> users=userService.getAllUser();
		return ResponseEntity.ok(users);	
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getuserById(@PathVariable int id)
	{
		logger.info("The client demands user with userId : {}",id);
		User user=  userService.getUserById(id);
		logger.info("The user to be sent back is {}",user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);
	}
	
	
	//Service controllers
	@GetMapping("/service/user/{userId}")
	public User getUserByIdService(@PathVariable int userId) {
		User user = this.userService.getUserByIdService(userId);
		return user;
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLogin userlogin)
	{
		User user=this.userService.login(userlogin);
		return ResponseEntity.ok(user);
	}
	

}
