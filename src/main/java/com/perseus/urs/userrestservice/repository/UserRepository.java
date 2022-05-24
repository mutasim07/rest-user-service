package com.perseus.urs.userrestservice.repository;

import com.perseus.urs.userrestservice.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	List<UserEntity> findByName(String name);
}
