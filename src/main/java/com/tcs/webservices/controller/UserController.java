package com.tcs.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.webservices.exceptions.UserNotFoundException;
import com.tcs.webservices.model.User;
import com.tcs.webservices.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		
		User savedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		log.warn("A new user has been created: {}", user.toString());
		return ResponseEntity.created(location).build();
	}
	
	//http://localhost:8080/users/1
	@GetMapping("users/{id}")
	public EntityModel<User> getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		if(user==null) {
			log.error("Invalid user ID {}", id);
			throw new UserNotFoundException("id:"+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
		entityModel.add(link.withRel("all-users"));
		log.info("The information of the user with ID {} has been retrieved", id);
		return entityModel;
	}
	
	@GetMapping("/users")
	public List<User>getUsers(){
		log.warn("Access to the User List");
		return userService.getUsers();
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
