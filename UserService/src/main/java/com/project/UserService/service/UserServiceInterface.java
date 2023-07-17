package com.project.UserService.service;

import java.util.List;
import java.util.Optional;


import com.project.UserService.entity.User;
import com.project.UserService.payloads.UserLogin;


public interface UserServiceInterface {
public User getUserById(int id);
List<User> getAllUser();
public void deleteUser(int id);
//public String register(User user);

public User login(UserLogin userlogin);
public User getUserByEmail(String email);
public String register(User user);


	

}
