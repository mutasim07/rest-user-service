package com.perseus.urs.userrestservice.controller;

import com.perseus.urs.userrestservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
	@Autowired
	private IUserService userService;
}
