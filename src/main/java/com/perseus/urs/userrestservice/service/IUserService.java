package com.perseus.urs.userrestservice.service;

import com.perseus.urs.userrestservice.domain.UserEntity;

import java.util.List;

public interface IUserService
{
	List<UserEntity> findByName(String name);
}
