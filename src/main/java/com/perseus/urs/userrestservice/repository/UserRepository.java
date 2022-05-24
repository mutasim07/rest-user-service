package com.perseus.urs.userrestservice.repository;

import com.perseus.urs.userrestservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
	List<User> findByName(String name);
}
