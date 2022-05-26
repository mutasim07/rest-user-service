package com.perseus.urs.userrestservice.transformer;

import com.perseus.urs.userrestservice.domain.UserEmailEntity;
import com.perseus.urs.userrestservice.model.UserEmailModel;
import org.springframework.stereotype.Component;

@Component("userEmailTransformer")
public class UserEmailTransformer implements Transformer<UserEmailEntity, UserEmailModel>
{
	@Override
	public UserEmailEntity toEntity(UserEmailModel userEmailModel)
	{
		return UserEmailEntity.builder()
				.emailId(userEmailModel.getEmailId() == null ? 0l : userEmailModel.getEmailId())
				.email(userEmailModel.getEmail())
				.build();
	}

	@Override
	public UserEmailModel toModel(UserEmailEntity userEmailEntity)
	{
		return UserEmailModel.builder()
				.emailId(userEmailEntity.getEmailId())
				.userId(userEmailEntity.getUser().getUserId())
				.email(userEmailEntity.getEmail())
				.build();
	}
}
