package com.project.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.UserService.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	@Query("select u from User u where u.email like :email")
	User searchByEmail(@Param("email")String email);

}
