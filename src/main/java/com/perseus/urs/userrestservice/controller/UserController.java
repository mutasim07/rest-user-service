package com.perseus.urs.userrestservice.controller;

import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;
import com.perseus.urs.userrestservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController
{
	@Autowired
	private UserService userService;
	@PostMapping("/user")
	public ResponseEntity<UserResponseModel> addUser(@RequestBody UserModel body) {
		UserResponseModel userResponseModel = userService.addUser(body);
		userResponseModel.setResultCode(0);
		userResponseModel.setResultDescription("User added successfully");
		return ResponseEntity.ok(userResponseModel);
	}

	@PutMapping("/user")
	public ResponseEntity<UserResponseModel> update(@RequestBody UserModel body) {
		UserResponseModel userResponseModel = userService.updateUser(body);
		userResponseModel.setResultCode(0);
		userResponseModel.setResultDescription("User updated successfully");
		return ResponseEntity.ok(userResponseModel);
	}

	@GetMapping("/user/name")
	public ResponseEntity<UsersResponseModel> getUsersByName(@RequestParam String firstName, @RequestParam String lastName) {
		UsersResponseModel usersResponseModel = userService.findByFirstNameAndLastName(firstName, lastName);
		usersResponseModel.setResultCode(0);
		usersResponseModel.setResultDescription("Users fetched successfully");
		return ResponseEntity.ok(usersResponseModel);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserResponseModel> getUsersById(@PathVariable long userId) {
		UserResponseModel userResponseModel = userService.findByUserId(userId);
		userResponseModel.setResultCode(0);
		userResponseModel.setResultDescription("User fetched successfully");
		return ResponseEntity.ok(userResponseModel);
	}
}
