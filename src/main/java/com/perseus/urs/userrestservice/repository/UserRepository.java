package com.perseus.urs.userrestservice.repository;

import com.perseus.urs.userrestservice.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	Optional<UserEntity> findByUserId(long userId);
	List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
	boolean existsByUserId(long id);
}
