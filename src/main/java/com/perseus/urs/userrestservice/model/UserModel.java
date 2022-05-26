package com.perseus.urs.userrestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserModel
{
	@JsonProperty("userId")
	private long userId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("emails")
	private List<UserEmailModel> emails;
	@JsonProperty("phones")
	private List<UserPhoneModel> phones;
}
