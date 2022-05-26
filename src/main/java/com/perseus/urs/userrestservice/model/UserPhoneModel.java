package com.perseus.urs.userrestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPhoneModel
{
	@JsonIgnore
	private Long userId;
	@JsonProperty("phoneId")
	private Long phoneId;
	@JsonProperty("phone")
	private String phone;
}
