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
@Table(name = "user_email")
public class UserEmailEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_id")
	private long emailId;
	@JsonIgnore
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@Column(name = "email")
	private String email;
}
