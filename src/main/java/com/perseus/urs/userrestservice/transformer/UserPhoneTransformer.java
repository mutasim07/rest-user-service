package com.perseus.urs.userrestservice.transformer;

import com.perseus.urs.userrestservice.domain.UserPhoneEntity;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import org.springframework.stereotype.Component;

@Component("UserPhoneTransformer")
public class UserPhoneTransformer implements Transformer<UserPhoneEntity, UserPhoneModel>
{
	@Override
	public UserPhoneEntity toEntity(UserPhoneModel userPhoneModel)
	{
		return UserPhoneEntity.builder()
				.phoneId(userPhoneModel.getPhoneId() == null ? 0l : userPhoneModel.getPhoneId())
				.phone(userPhoneModel.getPhone())
				.build();
	}

	@Override
	public UserPhoneModel toModel(UserPhoneEntity userPhoneEntity)
	{
		return UserPhoneModel.builder()
				.phoneId(userPhoneEntity.getPhoneId())
				.userId(userPhoneEntity.getUser().getUserId())
				.phone(userPhoneEntity.getPhone())
				.build();
	}
}
