package com.perseus.urs.userrestservice.repository;

import com.perseus.urs.userrestservice.domain.UserEmailEntity;
import com.perseus.urs.userrestservice.domain.UserPhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmailEntity, Long>
{
	UserEmailEntity findByEmail(String email);
}
