package com.perseus.urs.userrestservice.transformer;

import com.perseus.urs.userrestservice.domain.UserEmailEntity;
import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.domain.UserPhoneEntity;
import com.perseus.urs.userrestservice.model.UserEmailModel;
import com.perseus.urs.userrestservice.model.UserModel;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userTransformer")
public class UserTransformer implements Transformer<UserEntity, UserModel>
{
	@Autowired
	UserEmailTransformer userEmailTransformer;
	@Autowired
	UserPhoneTransformer userPhoneTransformer;

	@Override
	public UserEntity toEntity(UserModel userModel)
	{
		UserEntity userEntity = toUser(userModel);
		List<UserEmailEntity> userEmailEntities = toUserEmails(userModel.getEmails(), userEntity);
		userEntity.setEmails(userEmailEntities);
		List<UserPhoneEntity> userPhoneEntities = toUserPhones(userModel.getPhones(), userEntity);
		userEntity.setPhones(userPhoneEntities);
		return userEntity;
	}

	@Override
	public UserModel toModel(UserEntity userEntity)
	{
		return UserModel.builder()
				.userId(userEntity.getUserId())
				.firstName(userEntity.getFirstName())
				.lastName(userEntity.getLastName())
				.emails(userEmailTransformer.toModels(userEntity.getEmails()))
				.phones(userPhoneTransformer.toModels(userEntity.getPhones()))
				.build();
	}

	private UserEntity toUser(UserModel userModel)
	{
		return UserEntity.builder()
				.userId(userModel.getUserId())
				.firstName(userModel.getFirstName())
				.lastName(userModel.getLastName())
				.build();
	}

	private List<UserEmailEntity> toUserEmails(List<UserEmailModel> userEmails, UserEntity userEntity)
	{
		List<UserEmailEntity> userEmailEntities = userEmailTransformer.toEntities(userEmails);
		userEmailEntities.forEach(userEmailEntity -> userEmailEntity.setUser(userEntity));
		return userEmailEntities;
	}

	private List<UserPhoneEntity> toUserPhones(List<UserPhoneModel> userPhones, UserEntity userEntity)
	{
		List<UserPhoneEntity> userPhoneEntities = userPhoneTransformer.toEntities(userPhones);
		userPhoneEntities.forEach(userPhoneEntity -> userPhoneEntity.setUser(userEntity));
		return userPhoneEntities;
	}
}
