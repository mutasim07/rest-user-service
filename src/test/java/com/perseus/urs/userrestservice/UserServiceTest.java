package com.perseus.urs.userrestservice;

import com.perseus.urs.userrestservice.exception.NotFoundException;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.repository.UserEmailRepository;
import com.perseus.urs.userrestservice.repository.UserPhoneRepository;
import com.perseus.urs.userrestservice.repository.UserRepository;
import com.perseus.urs.userrestservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest
{
	@Autowired
	private UserService userService;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private UserEmailRepository userEmailRepository;
	@MockBean
	private UserPhoneRepository userPhoneRepository;


	@Test
	public void findByUserId_invalidUserId_Failure()
	{
		long userId = -1;
		Mockito.when(userRepository.findByUserId(userId)).thenReturn(Optional.empty());
		Throwable exception = assertThrows(
				NotFoundException.class,
				() -> this.userService.findByUserId(userId));

		Assertions.assertEquals("User Id not found",exception.getMessage());
	}

	private UserModel getInvalidUser()
	{
		return UserModel.builder()
				.userId(-1)
				.build();
	}
}
