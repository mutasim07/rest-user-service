package com.perseus.urs.userrestservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseModel
{
	@JsonProperty("resultCode")
	private Integer resultCode = null;

	@JsonProperty("resultDescription")
	private String resultDescription = null;
}
