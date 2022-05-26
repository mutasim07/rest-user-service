package com.perseus.urs.userrestservice.service;

import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;


public interface UserService
{
	UserResponseModel findByUserId(long userId);
	UsersResponseModel findByFirstNameAndLastName(String firstName, String lastName);
	UserResponseModel addOrUpdateUser(UserModel userModel);
}
