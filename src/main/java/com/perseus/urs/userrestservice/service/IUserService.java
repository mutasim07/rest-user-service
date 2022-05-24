package com.perseus.urs.userrestservice.service;

import com.perseus.urs.userrestservice.model.User;

import java.util.List;

public interface IUserService
{
	List<User> findByName(String name);
}
