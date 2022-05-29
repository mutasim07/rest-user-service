package com.perseus.urs.userrestservice;

import com.perseus.urs.userrestservice.domain.UserEmailEntity;
import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.domain.UserPhoneEntity;
import com.perseus.urs.userrestservice.exception.BadDataException;
import com.perseus.urs.userrestservice.exception.NotFoundException;
import com.perseus.urs.userrestservice.model.UserEmailModel;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import com.perseus.urs.userrestservice.model.response.UserEmailResponseModel;
import com.perseus.urs.userrestservice.model.response.UserPhoneResponseModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;
import com.perseus.urs.userrestservice.repository.UserEmailRepository;
import com.perseus.urs.userrestservice.repository.UserPhoneRepository;
import com.perseus.urs.userrestservice.repository.UserRepository;
import com.perseus.urs.userrestservice.service.UserService;
import com.perseus.urs.userrestservice.transformer.Transformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
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
	@MockBean
	private Transformer<UserEntity, UserModel> userTransformer;
	@MockBean
	private Transformer<UserEmailEntity, UserEmailModel> userEmailTransformer;
	@MockBean
	private Transformer<UserPhoneEntity, UserPhoneModel> userPhoneTransformer;

	@Test
	public void findByUserId_invalidUserId_Failure()
	{
		long userId = -1;
		Mockito.when(userRepository.findByUserId(userId)).thenReturn(Optional.empty());
		Throwable exception = assertThrows(NotFoundException.class, () -> this.userService.findByUserId(userId));
		Assertions.assertEquals("User Id not found", exception.getMessage());
	}

	@Test
	public void findByUserId_validUserId_Success()
	{
		long userId = 1;
		Mockito.when(userRepository.findByUserId(userId)).thenReturn(Optional.of(new UserEntity()));
		Mockito.when(userTransformer.toModel(new UserEntity())).thenReturn(new UserModel());
		UserResponseModel userResponseModel = this.userService.findByUserId(userId);
		Assertions.assertEquals(UserResponseModel.class, userResponseModel.getClass());
	}

	@Test
	public void findByFirstNameAndLastName_validName_Success()
	{
		String firstName = "FirstName";
		String lastName = "LastName";
		Mockito.when(userRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(new ArrayList<>());
		Mockito.when(userTransformer.toModels(new ArrayList<>())).thenReturn(new ArrayList<>());
		UsersResponseModel usersResponseModel = this.userService.findByFirstNameAndLastName(firstName, lastName);
		Assertions.assertEquals(UsersResponseModel.class, usersResponseModel.getClass());
	}

	@Test
	public void addUser_validUser_Success()
	{
		UserEntity userEntity = getUserEntity();
		UserModel userModel = getUserModel();
		Mockito.when(userTransformer.toEntity(userModel)).thenReturn(userEntity);
		Mockito.when(userRepository.save(getUserEntity())).thenReturn(getUserEntity());
		Mockito.when(userTransformer.toModel(getUserEntity())).thenReturn(getUserModel());
		UserResponseModel userResponseModel = this.userService.addUser(getUserModel());
		Assertions.assertEquals(UserResponseModel.class, userResponseModel.getClass());
	}

	@Test
	public void addUserEmail_validUserEmail_Success()
	{
		UserEmailEntity userEmailEntity = getUserEmailEntity();
		UserEmailModel userEmailModel = getUserEmailModel();
		UserEntity userEntity = getUserEntity();
		Mockito.when(userEmailTransformer.toEntity(userEmailModel)).thenReturn(userEmailEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userEmailRepository.findByEmail(userEmailModel.getEmail())).thenReturn(null);
		Mockito.when(userEmailRepository.saveAndFlush(userEmailEntity)).thenReturn(userEmailEntity);
		UserEmailResponseModel userEmailResponseModel = this.userService.addUserEmail(1, userEmailModel);
		Assertions.assertEquals(UserEmailResponseModel.class, userEmailResponseModel.getClass());
	}

	@Test
	public void addUserEmail_duplicateUserEmail_Failure()
	{
		UserEmailEntity userEmailEntity = getUserEmailEntity();
		UserEmailModel userEmailModel = getUserEmailModel();
		UserEntity userEntity = getUserEntity();
		Mockito.when(userEmailTransformer.toEntity(userEmailModel)).thenReturn(userEmailEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userEmailRepository.findByEmail(userEmailModel.getEmail())).thenReturn(userEmailEntity);
		Throwable exception = assertThrows(BadDataException.class, () -> this.userService.addUserEmail(1, userEmailModel));
		Assertions.assertEquals("Email already exists", exception.getMessage());
	}

	@Test
	public void addUserPhone_validUserPhone_Success()
	{
		UserPhoneEntity userPhoneEntity = getUserPhoneEntity();
		UserPhoneModel userPhoneModel = getUserPhoneModel();
		UserEntity userEntity = getUserEntity();
		Mockito.when(userPhoneTransformer.toEntity(userPhoneModel)).thenReturn(userPhoneEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userPhoneRepository.findByPhone(userPhoneModel.getPhone())).thenReturn(null);
		Mockito.when(userPhoneRepository.saveAndFlush(userPhoneEntity)).thenReturn(userPhoneEntity);
		UserPhoneResponseModel userPhoneResponseModel = this.userService.addUserPhone(1, userPhoneModel);
		Assertions.assertEquals(UserPhoneResponseModel.class, userPhoneResponseModel.getClass());
	}

	@Test
	public void addUserPhone_duplicateUserPhone_Failure()
	{
		UserPhoneEntity userPhoneEntity = getUserPhoneEntity();
		UserPhoneModel userPhoneModel = getUserPhoneModel();
		UserEntity userEntity = getUserEntity();
		Mockito.when(userPhoneTransformer.toEntity(userPhoneModel)).thenReturn(userPhoneEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userPhoneRepository.findByPhone(userPhoneModel.getPhone())).thenReturn(userPhoneEntity);
		Throwable exception = assertThrows(BadDataException.class, () -> this.userService.addUserPhone(1, userPhoneModel));
		Assertions.assertEquals("Phone already exists", exception.getMessage());
	}

	@Test
	public void updateUserEmail_validUserEmail_Success()
	{
		UserEmailEntity userEmailEntity = getUserEmailEntity();
		UserEmailModel userEmailModel = getUserEmailModel();
		userEmailModel.setEmailId(1l);
		userEmailEntity.setEmailId(1l);
		UserEntity userEntity = getUserEntity();
		userEntity.setEmails(Arrays.asList(userEmailEntity));
		Mockito.when(userEmailTransformer.toEntity(userEmailModel)).thenReturn(userEmailEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userEmailRepository.findByEmail(userEmailModel.getEmail())).thenReturn(userEmailEntity);
		Mockito.when(userEmailRepository.saveAndFlush(userEmailEntity)).thenReturn(userEmailEntity);
		UserEmailResponseModel userEmailResponseModel = this.userService.updateUserEmail(1, userEmailModel);
		Assertions.assertEquals(UserEmailResponseModel.class, userEmailResponseModel.getClass());
	}

	@Test
	public void updateUserPhone_validUserPhone_Success()
	{
		UserPhoneEntity userPhoneEntity = getUserPhoneEntity();
		UserPhoneModel userPhoneModel = getUserPhoneModel();
		userPhoneModel.setPhoneId(1l);
		userPhoneEntity.setPhoneId(1l);
		UserEntity userEntity = getUserEntity();
		userEntity.setPhones(Arrays.asList(userPhoneEntity));
		Mockito.when(userPhoneTransformer.toEntity(userPhoneModel)).thenReturn(userPhoneEntity);
		Mockito.when(userRepository.findByUserId(1)).thenReturn(Optional.of(userEntity));
		Mockito.when(userPhoneRepository.findByPhone(userPhoneModel.getPhone())).thenReturn(userPhoneEntity);
		Mockito.when(userPhoneRepository.saveAndFlush(userPhoneEntity)).thenReturn(userPhoneEntity);
		UserPhoneResponseModel userPhoneResponseModel = this.userService.updateUserPhone(1, userPhoneModel);
		Assertions.assertEquals(UserPhoneResponseModel.class, userPhoneResponseModel.getClass());
	}

	@Test
	public void deleteUser_invalidUserId_Success()
	{
		long userId = 1;
		Mockito.when(userRepository.existsByUserId(userId)).thenReturn(false);
		Throwable exception = assertThrows(NotFoundException.class, () -> this.userService.deleteUser(userId));
		Assertions.assertEquals("User id not found", exception.getMessage());

	}

	private UserModel getUserModel()
	{
		return UserModel.builder()
				.firstName("FirstName")
				.lastName("LastName")
				.build();
	}

	private UserEmailModel getUserEmailModel()
	{
		return UserEmailModel.builder()
				.email("firstNameLastName@example.com")
				.build();
	}

	private UserPhoneModel getUserPhoneModel()
	{
		return UserPhoneModel.builder()
				.phone("1234567890")
				.build();
	}

	private UserEntity getUserEntity()
	{
		return UserEntity.builder()
				.firstName("FirstName")
				.lastName("LastName")
				.build();
	}

	private UserEmailEntity getUserEmailEntity()
	{
		return UserEmailEntity.builder()
				.email("firstNameLastName@example.com")
				.build();
	}

	private UserPhoneEntity getUserPhoneEntity()
	{
		return UserPhoneEntity.builder()
				.phone("1234567890")
				.build();
	}


}
