package com.perseus.urs.userrestservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perseus.urs.userrestservice.model.UserModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UsersResponseModel extends CommonResponseModel
{
	@JsonProperty("users")
	private List<UserModel> users = null;
}
