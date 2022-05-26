package com.perseus.urs.userrestservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "user_phone")
public class UserPhoneEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id")
	private long phoneId;
	@JsonIgnore
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@Column(name = "phone")
	private String phone;
}
