package com.perseus.urs.userrestservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perseus.urs.userrestservice.model.UserPhoneModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UserPhoneResponseModel extends CommonResponseModel
{
	@JsonProperty("userPhone")
	private UserPhoneModel userPhone = null;
	public UserPhoneResponseModel user(UserPhoneModel userPhone) {
		this.userPhone = userPhone;
		return this;
	}
}
