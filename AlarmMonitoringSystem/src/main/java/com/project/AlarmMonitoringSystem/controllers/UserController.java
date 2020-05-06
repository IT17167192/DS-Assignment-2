package com.project.AlarmMonitoringSystem.controllers;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.AlarmMonitoringSystem.models.Sensor;
import com.project.AlarmMonitoringSystem.models.User;
import com.project.AlarmMonitoringSystem.repositories.SensorRepository;
import com.project.AlarmMonitoringSystem.repositories.UserRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class UserController {
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	//method return all the users
	@GetMapping("/users")
	Collection<User> users(){
		return userRepository.findAll();
	}
	
	//method find and return a user by id
	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id){
		Optional<User> user = userRepository.findById(id);
		return user.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	//method creates a user
	@PostMapping("/user")
	ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException{
		User result = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/user" + result.getId())).body(result);
	}
	
	//method find and update a user by id
	@PutMapping("/user/{id}")
	ResponseEntity<User> updateUser(@Valid @RequestBody User user){
		User result = userRepository.save(user);
		return ResponseEntity.ok().body(result);
	}
	
	//method find and delete a user by id
	@DeleteMapping("/user/{id}")
	ResponseEntity<?> deleteUser(@PathVariable Long id){
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
