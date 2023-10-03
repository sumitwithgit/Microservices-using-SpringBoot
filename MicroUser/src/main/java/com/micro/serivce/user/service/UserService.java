package com.micro.serivce.user.service;

import java.util.List;

import com.micro.serivce.user.entities.User;
import com.micro.serivce.user.exception.UserException;

public interface UserService {

	User createUser(User user) throws UserException;
	
	List<User> getAllUser() throws UserException;
	
	User getUserById(String userId) throws UserException;
	
	void deleteUserById(String userId) throws UserException;
	
	User updateUserById(String userId, User user) throws UserException;
}
