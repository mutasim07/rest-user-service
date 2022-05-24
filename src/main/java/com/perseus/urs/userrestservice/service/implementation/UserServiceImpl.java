package com.perseus.urs.userrestservice.service.implementation;

import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService
{

	@Override
	public List<UserEntity> findByName(String name)
	{
		return null;
	}
}
