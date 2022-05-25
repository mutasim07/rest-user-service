package com.perseus.urs.userrestservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perseus.urs.userrestservice.model.UserModel;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserResponseModel extends CommonResponseModel
{
	@JsonProperty("user")
	private UserModel user = null;
	public UserResponseModel user(UserModel user) {
		this.user = user;
		return this;
	}
}
