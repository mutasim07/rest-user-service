package com.perseus.urs.userrestservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "user_phone")
public class UserPhoneEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_id")
	private long phoneId;
	@JsonIgnore
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@Column(name = "phone")
	private String phone;
}
