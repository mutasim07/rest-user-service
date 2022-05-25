package com.perseus.urs.userrestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel
{
	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
}
