package com.project.UserService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.stream.Collectors;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.UserService.repository.UserRepository;

import com.project.UserService.payloads.UserLogin;

import com.project.UserService.entity.User;

import com.project.UserService.service.UserService;

@SpringBootTest

class UserApplicationTests {

@Autowired
private UserService service;


@MockBean
private UserRepository repository;

@Test

public void getUsersTest() {

when(repository.findAll()).thenReturn(Stream

.of(new User(376, "Danile", "danile@gmail.com", "Danile@123"),

new User(958, "Harsh", "harsh@gmail.com", "Harsh@123")).collect(Collectors.toList()));

assertEquals(2, service.getAllUser().size());

}


@Test

public void searchByEmail() {

final var email = "danile@gmail.com";

final var user = new User(376, "Danile", "danile@gmail.com", "Danile@123", 1);

when(repository.searchByEmail(email)).thenReturn(user);

assertEquals(user, service.getUserByEmail(email));

}

@Test

public void searchById() {

final var id=2;

Optional<User> user = Optional.ofNullable(new User(376, "Danile", "danile@gmail.com", "Danile@123", 2));

when(repository.findById(id)).thenReturn(user);

Optional<User> response= Optional.ofNullable(service.getUserById(id));

assertEquals(user, response);

}

@Test

public void saveUserTest() {

final var user = new User(376, "Danile", "danile@gmail.com", "Daniel@123", 1);

when(repository.save(user)).thenReturn(user);

assertEquals("successfully registered", service.register(user));

}

@Test
void testGetAllUsers() {
	List<User> users= new ArrayList<>();
	users.add(new User(376, "Danile", "danile@gmail.com", "Daniel@123", 1));
	users.add(new User(377, "Danile", "danile@gmail.com", "Daniel@123", 1));
	
	when(repository.findAll()).thenReturn(users);
	
	List<User> response= service.getAllUser();
	assertEquals(2, response.size());
	
}

@Test
void testlogin() {
	
	UserLogin userLogin= new UserLogin("danile@gmail.com","Daniel@123","Daniel@1234");
	String email= userLogin.getEmail();
	String password= userLogin.getPassword();
	
	final var user = new User(376, "Danile", "danile@gmail.com", "Daniel@123", 1);
	when(repository.searchByEmail(email)).thenReturn(user);
	
	User response= service.login(userLogin);
	
	assertEquals(user,response);
	
}

@Test
void testResgister() {
	
	final var user = new User(376, "Danile", "danile@gmail.com", "Daniel@123", 1);
	String dummy= "successfully registered";
	when(repository.searchByEmail(user.getEmail())).thenReturn(user);
	
	String response= service.register(user);
	
	assertEquals(dummy, response);
	
}

}
