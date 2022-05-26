package com.perseus.urs.userrestservice.service.implementation;

import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.response.UserResponseModel;
import com.perseus.urs.userrestservice.model.response.UsersResponseModel;
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
		//throw not found exception
		return null;
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
		UserEntity userEntity = userTransformer.toEntity(userModel);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return toUserResponseModel(savedUserEntity);
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
}
