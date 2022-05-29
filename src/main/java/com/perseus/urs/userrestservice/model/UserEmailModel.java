package com.perseus.urs.userrestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailModel
{
	@JsonIgnore
	private Long userId;
	@JsonProperty("emailId")
	private Long emailId;
	@JsonProperty("email")
	private String email;
}
