package com.perseus.urs.userrestservice.service.implementation;

import com.perseus.urs.userrestservice.domain.UserEmailEntity;
import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.domain.UserPhoneEntity;
import com.perseus.urs.userrestservice.exception.BadDataException;
import com.perseus.urs.userrestservice.exception.NotFoundException;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;
import com.perseus.urs.userrestservice.repository.UserEmailRepository;
import com.perseus.urs.userrestservice.repository.UserPhoneRepository;
import com.perseus.urs.userrestservice.repository.UserRepository;
import com.perseus.urs.userrestservice.service.UserService;
import com.perseus.urs.userrestservice.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserEmailRepository userEmailRepository;
	@Autowired
	private UserPhoneRepository userPhoneRepository;
	@Autowired
	private Transformer<UserEntity, UserModel> userTransformer;

	@Override
	public UserResponseModel findByUserId(long userId)
	{
		Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);
		if(userEntityOptional.isPresent())
		{
			UserEntity savedUserEntity = userRepository.save(userEntityOptional.get());
			return toUserResponseModel(savedUserEntity);
		}
		throw new NotFoundException("User Id not found");
	}

	@Override
	public UsersResponseModel findByFirstNameAndLastName(String firstName, String lastName)
	{
		List<UserEntity> userEntities = userRepository.findByFirstNameAndLastName(firstName, lastName);
		return toUsersResponseModel(userEntities);
	}

	@Override
	public UserResponseModel addUser(UserModel userModel)
	{
		userModel.setUserId(0l);
		if (userModel.getEmails() != null)
			userModel.getEmails().forEach(email -> email.setEmailId(0L));
		if (userModel.getPhones() != null)
			userModel.getPhones().forEach(phone -> phone.setPhoneId(0L));

		UserEntity userEntity = userTransformer.toEntity(userModel);
		validateUser(userEntity);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return toUserResponseModel(savedUserEntity);
	}

	@Override
	public UserResponseModel updateUser(UserModel userModel)
	{
		UserEntity userEntity = userTransformer.toEntity(userModel);
		validateMandatoryUserId(userEntity);
		validateUser(userEntity);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return toUserResponseModel(savedUserEntity);
	}

	@Override
	public void deleteUser(long userId)
	{
		if(userRepository.existsByUserId(userId))
			userRepository.deleteById(userId);
		else
			throw new NotFoundException("User id not found", userId);
	}

	private UserResponseModel toUserResponseModel(UserEntity userEntity)
	{
		UserModel userModel = userTransformer.toModel(userEntity);
		return UserResponseModel.builder()
				.user(userModel)
				.build();
	}

	private UsersResponseModel toUsersResponseModel(List<UserEntity> userEntities)
	{
		List<UserModel> userModels = userTransformer.toModels(userEntities);
		return UsersResponseModel.builder()
				.users(userModels)
				.build();
	}

	private void validateUser(UserEntity userEntity)
	{
		validateUserId(userEntity);
		validateUserEmailUnique(userEntity);
		validateUserPhoneUnique(userEntity);
	}

	private void validateUserId(UserEntity userEntity)
	{
		if (userEntity.getUserId() > 0 && !userRepository.existsByUserId(userEntity.getUserId()))
			throw new NotFoundException("User id not found", userEntity.getUserId());
	}

	private void validateMandatoryUserId(UserEntity userEntity)
	{
		if (userEntity.getUserId() == 0)
			throw new BadDataException("User id must be provided");
	}

	private void validateUserEmailUnique(UserEntity user)
	{
		if (user.getEmails() != null)
		{
			user.getEmails().forEach(x -> {
				if (!isEmailUnique(x))
					throw new BadDataException("Email already exists", x.getEmail());
			});
		}
	}

	private void validateUserPhoneUnique(UserEntity user)
	{
		if (user.getPhones() != null)
		{
			user.getPhones().forEach(x -> {
				if (!isPhoneUnique(x))
					throw new BadDataException("Phone already exists", x.getPhone());
			});
		}
	}

	private boolean isPhoneUnique(UserPhoneEntity userPhoneEntity)
	{
		UserPhoneEntity entity = userPhoneRepository.findByPhone(userPhoneEntity.getPhone());
		if (entity == null)
			return true;
		if (entity.getUser().getUserId() == userPhoneEntity.getUser().getUserId())
		{
			userPhoneEntity.setPhoneId(entity.getPhoneId());
			return true;
		}
		return false;
	}

	private boolean isEmailUnique(UserEmailEntity userEmailEntity)
	{
		UserEmailEntity entity = userEmailRepository.findByEmail(userEmailEntity.getEmail());
		if (entity == null)
			return true;
		if (entity.getUser().getUserId() == userEmailEntity.getUser().getUserId())
		{
			userEmailEntity.setEmailId(entity.getEmailId());
			return true;
		}
		return false;
	}
}
