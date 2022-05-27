package com.perseus.urs.userrestservice.service;

import com.perseus.urs.userrestservice.model.UserEmailModel;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import com.perseus.urs.userrestservice.model.response.UserEmailResponseModel;
import com.perseus.urs.userrestservice.model.response.UserPhoneResponseModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;


public interface UserService
{
	UserResponseModel findByUserId(long userId);
	UsersResponseModel findByFirstNameAndLastName(String firstName, String lastName);
	UserResponseModel addUser(UserModel userModel);
	UserResponseModel updateUser(UserModel userModel);
	void deleteUser(long userId);
	UserEmailResponseModel addUserEmail(long userId, UserEmailModel userEmailModel);
	UserPhoneResponseModel addUserPhone(long userId, UserPhoneModel userPhoneModel);
	UserEmailResponseModel updateUserEmail(long userId, UserEmailModel userEmailModel);
	UserPhoneResponseModel updateUserPhone(long userId, UserPhoneModel userPhoneModel);
}
