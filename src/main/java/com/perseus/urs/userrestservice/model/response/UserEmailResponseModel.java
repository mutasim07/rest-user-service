package com.perseus.urs.userrestservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perseus.urs.userrestservice.model.UserEmailModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UserEmailResponseModel extends CommonResponseModel
{
	@JsonProperty("userEmail")
	private UserEmailModel userEmail = null;
	public UserEmailResponseModel user(UserEmailModel userEmail) {
		this.userEmail = userEmail;
		return this;
	}
}
