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
				.id(userModel.getId())
				.name(userModel.getName())
				.build();
	}

	@Override
	public UserModel toModel(UserEntity userEntity) {
		return UserModel.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.build();
	}
}
