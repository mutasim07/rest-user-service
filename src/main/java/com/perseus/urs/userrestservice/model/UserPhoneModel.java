package com.perseus.urs.userrestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPhoneModel
{
	@JsonIgnore
	private Long userId;
	@JsonProperty("phoneId")
	private Long phoneId;
	@JsonProperty("phone")
	private String phone;
}
