package com.perseus.urs.userrestservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "user_email")
public class UserEmailEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email_id")
	private long emailId;
	@JsonIgnore
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@Column(name = "email")
	private String email;
}
