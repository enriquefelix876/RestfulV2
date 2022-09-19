package com.tcs.webservices.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.tcs.webservices.model.User;
import com.tcs.webservices.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	//Create a User
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	//Retrieve a specific User
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	//Retrieve a List of Users
	public List<User>getUsers(){
		return userRepository.findAll();
	}
	
	//Delete a user
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
