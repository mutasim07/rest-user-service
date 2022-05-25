package com.perseus.urs.userrestservice.service;

import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;


public interface UserService
{
	UserResponseModel findById(long id);
	UsersResponseModel findByName(String name);
	UserResponseModel addUser(UserModel userModel);
}
