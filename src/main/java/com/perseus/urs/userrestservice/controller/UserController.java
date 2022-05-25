package com.perseus.urs.userrestservice.controller;

import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;
import com.perseus.urs.userrestservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

	@GetMapping("/user/name/{name}")
	public ResponseEntity<UsersResponseModel> getUsersByName(@PathVariable String name) {
		UsersResponseModel usersResponseModel = userService.findByName(name);
		usersResponseModel.setResultCode(0);
		usersResponseModel.setResultDescription("User fetched successfully");
		return ResponseEntity.ok(usersResponseModel);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponseModel> getUsersById(@PathVariable long id) {
		UserResponseModel userResponseModel = userService.findById(id);
		userResponseModel.setResultCode(0);
		userResponseModel.setResultDescription("User added successfully");
		return ResponseEntity.ok(userResponseModel);
	}
}
