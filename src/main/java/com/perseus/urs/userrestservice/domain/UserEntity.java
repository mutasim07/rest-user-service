package com.perseus.urs.userrestservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Entity
@Table(name = "user")
public class UserEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(targetEntity = UserEmailEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private List<UserEmailEntity> emails;

	@OneToMany(targetEntity = UserPhoneEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private List<UserPhoneEntity> phones;
}
