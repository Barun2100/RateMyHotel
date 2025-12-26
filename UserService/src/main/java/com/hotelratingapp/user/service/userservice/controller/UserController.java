package com.hotelratingapp.user.service.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hotelratingapp.user.service.userservice.entities.User;
import com.hotelratingapp.user.service.userservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
	@Autowired
	private UserService userService;
    //create user
	@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user= userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}

    //all user get
	@GetMapping()
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
    //update user
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user){
		User updatedUser =userService.updateUser(userId, user);
		return ResponseEntity.ok(updatedUser);
	}

    //delete user by id
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable String userId){
		boolean isDeleted= userService.deleteUserById(userId);
		return ResponseEntity.ok(isDeleted);
	}
}
