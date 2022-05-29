package com.perseus.urs.userrestservice.service.implementation;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	@Autowired
	private Transformer<UserEmailEntity, UserEmailModel> userEmailTransformer;
	@Autowired
	private Transformer<UserPhoneEntity, UserPhoneModel> userPhoneTransformer;

	@Override
	public UserResponseModel findByUserId(long userId)
	{
		UserEntity userEntity = getByUserId(userId);
		return toUserResponseModel(userEntity);
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
	public UserEmailResponseModel addUserEmail(long userId, UserEmailModel userEmailModel)
	{
		userEmailModel.setEmailId(0l);
		UserEmailEntity userEmailEntity = userEmailTransformer.toEntity(userEmailModel);
		UserEntity userEntity = getByUserId(userId);
		userEmailEntity.setUser(userEntity);
		if (userEntity.hasEmail(userEmailEntity))
			throw new DuplicateKeyException("Email already exists");
		if (userEmailRepository.findByEmail(userEmailEntity.getEmail()) != null)
			throw new BadDataException("Email already exists");
		UserEmailEntity savedUserEmailEntity = userEmailRepository.saveAndFlush(userEmailEntity);

		return toUserEmailResponseModel(savedUserEmailEntity);
	}

	@Override
	public UserPhoneResponseModel addUserPhone(long userId, UserPhoneModel userPhoneModel)
	{
		userPhoneModel.setPhoneId(0l);
		UserPhoneEntity userPhoneEntity = userPhoneTransformer.toEntity(userPhoneModel);
		UserEntity userEntity = getByUserId(userId);
		userPhoneEntity.setUser(userEntity);
		if (userEntity.hasPhone(userPhoneEntity))
			throw new DuplicateKeyException("Phone already exists");
		if (userPhoneRepository.findByPhone(userPhoneEntity.getPhone()) != null)
			throw new BadDataException("Phone already exists");
		UserPhoneEntity savedUserPhoneEntity = userPhoneRepository.saveAndFlush(userPhoneEntity);

		return toUserPhoneResponseModel(savedUserPhoneEntity);
	}

	@Override
	public UserEmailResponseModel updateUserEmail(long userId, UserEmailModel userEmailModel)
	{
		UserEmailEntity userEmailEntity = userEmailTransformer.toEntity(userEmailModel);
		validateMandatoryEmailId(userEmailEntity);
		UserEntity userEntity = getByUserId(userId);
		if (!userEntity.hasEmail(userEmailEntity))
			throw new NotFoundException("Email not found");
		userEmailEntity.setUser(userEntity);
		if (!isEmailUnique(userEmailEntity))
			throw new DuplicateKeyException("Email already exists");
		UserEmailEntity savedUserEmailEntity = userEmailRepository.saveAndFlush(userEmailEntity);

		return toUserEmailResponseModel(savedUserEmailEntity);
	}

	@Override
	public UserPhoneResponseModel updateUserPhone(long userId, UserPhoneModel userPhoneModel)
	{
		UserPhoneEntity userPhoneEntity = userPhoneTransformer.toEntity(userPhoneModel);
		validateMandatoryPhoneId(userPhoneEntity);
		UserEntity userEntity = getByUserId(userId);
		if (!userEntity.hasPhone(userPhoneEntity))
			throw new NotFoundException("Phone not found");
		userPhoneEntity.setUser(userEntity);
		if (!isPhoneUnique(userPhoneEntity))
			throw new DuplicateKeyException("Phone already exists");
		UserPhoneEntity savedUserPhoneEntity = userPhoneRepository.saveAndFlush(userPhoneEntity);

		return toUserPhoneResponseModel(savedUserPhoneEntity);
	}

	@Override
	public UserResponseModel updateUser(UserModel userModel)
	{
		UserEntity userEntity = userTransformer.toEntity(userModel);
		validateMandatoryUserId(userEntity);
		if(userEntity.getEmails() != null)
			validateMandatoryUserEmailIds(userEntity.getEmails());
		if(userEntity.getPhones() != null)
			validateMandatoryUserPhoneIds(userEntity.getPhones());
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

	private UserEntity getByUserId(long userId)
	{
		Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);
		if(userEntityOptional.isPresent())
		{
			return userEntityOptional.get();
		}
		throw new NotFoundException("User Id not found");
	}


	private UserResponseModel toUserResponseModel(UserEntity userEntity)
	{
		UserModel userModel = userTransformer.toModel(userEntity);
		return UserResponseModel.builder()
				.user(userModel)
				.build();
	}

	private UserEmailResponseModel toUserEmailResponseModel(UserEmailEntity userEmailEntity)
	{
		UserEmailModel userEmailModel = userEmailTransformer.toModel(userEmailEntity);
		return UserEmailResponseModel.builder()
				.userEmail(userEmailModel)
				.build();
	}

	private UserPhoneResponseModel toUserPhoneResponseModel(UserPhoneEntity userPhoneEntity)
	{
		UserPhoneModel userPhoneModel = userPhoneTransformer.toModel(userPhoneEntity);
		return UserPhoneResponseModel.builder()
				.userPhone(userPhoneModel)
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
		validateUserId(userEntity.getUserId());
		validateUserEmailUnique(userEntity);
		validateUserPhoneUnique(userEntity);
	}

	private void validateUserId(long userId)
	{
		if (userId > 0 && !userRepository.existsByUserId(userId))
			throw new NotFoundException("User id not found", userId);
	}

	private void validateMandatoryUserId(UserEntity userEntity)
	{
		if (userEntity.getUserId() == 0)
			throw new BadDataException("User id must be provided");
	}

	private void validateMandatoryUserEmailIds(List<UserEmailEntity> userEmailEntities)
	{
		userEmailEntities.forEach(x -> {
			validateMandatoryEmailId(x);
		});
	}

	private void validateMandatoryUserPhoneIds(List<UserPhoneEntity> userPhoneEntities)
	{
		userPhoneEntities.forEach(x -> {
			validateMandatoryPhoneId(x);
		});
	}

	private void validateMandatoryEmailId(UserEmailEntity userEmailEntity)
	{
		if (userEmailEntity.getEmailId() == 0)
			throw new BadDataException("Email id must be provided");
	}

	private void validateMandatoryPhoneId(UserPhoneEntity userPhoneEntity)
	{
		if (userPhoneEntity.getPhoneId() == 0)
			throw new BadDataException("Phone id must be provided");
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
