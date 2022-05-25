package com.perseus.urs.userrestservice.repository;

import com.perseus.urs.userrestservice.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	List<UserEntity> findByName(String name);
}
