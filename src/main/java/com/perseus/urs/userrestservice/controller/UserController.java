package com.perseus.urs.userrestservice.controller;

import com.perseus.urs.userrestservice.model.UserEmailModel;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import com.perseus.urs.userrestservice.model.response.*;
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

	@PostMapping("/user/{userId}/email")
	public ResponseEntity<UserEmailResponseModel> addUserEmail(@PathVariable long userId, @RequestBody UserEmailModel body) {
		UserEmailResponseModel userEmailResponseModel = userService.addUserEmail(userId, body);
		userEmailResponseModel.setResultCode(0);
		userEmailResponseModel.setResultDescription("User email added successfully");
		return ResponseEntity.ok(userEmailResponseModel);
	}

	@PutMapping("/user/{userId}/email")
	public ResponseEntity<UserEmailResponseModel> updateUserEmail(@PathVariable long userId, @RequestBody UserEmailModel body) {
		UserEmailResponseModel userEmailResponseModel = userService.updateUserEmail(userId, body);
		userEmailResponseModel.setResultCode(0);
		userEmailResponseModel.setResultDescription("User email added successfully");
		return ResponseEntity.ok(userEmailResponseModel);
	}

	@PostMapping("/user/{userId}/phone")
	public ResponseEntity<UserPhoneResponseModel> addUserPhone(@PathVariable long userId, @RequestBody UserPhoneModel body) {
		UserPhoneResponseModel userPhoneResponseModel = userService.addUserPhone(userId, body);
		userPhoneResponseModel.setResultCode(0);
		userPhoneResponseModel.setResultDescription("User phone added successfully");
		return ResponseEntity.ok(userPhoneResponseModel);
	}

	@PutMapping("/user/{userId}/phone")
	public ResponseEntity<UserPhoneResponseModel> updateUserPhone(@PathVariable long userId, @RequestBody UserPhoneModel body) {
		UserPhoneResponseModel userPhoneResponseModel = userService.updateUserPhone(userId, body);
		userPhoneResponseModel.setResultCode(0);
		userPhoneResponseModel.setResultDescription("User phone added successfully");
		return ResponseEntity.ok(userPhoneResponseModel);
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

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<CommonResponseModel> deleteUserById(@PathVariable long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(new CommonResponseModel(0,"User deleted successfully"));
	}
}
