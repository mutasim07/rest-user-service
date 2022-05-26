package com.perseus.urs.userrestservice.transformer;

import com.perseus.urs.userrestservice.domain.UserEntity;
import com.perseus.urs.userrestservice.model.UserModel;
import org.springframework.stereotype.Component;

@Component("userTransformer")
public class UserTransformer implements Transformer<UserEntity, UserModel>
{
	@Override
	public UserEntity toEntity(UserModel userModel) {

		return UserEntity.builder()
				.userId(userModel.getUserId())
				.firstName(userModel.getFirstName())
				.lastName(userModel.getLastName())
				.build();
	}

	@Override
	public UserModel toModel(UserEntity userEntity) {
		return UserModel.builder()
				.userId(userEntity.getUserId())
				.firstName(userEntity.getFirstName())
				.lastName(userEntity.getLastName())
				.build();
	}
}
