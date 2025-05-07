package com.leadDashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer	> {
	User getByEmail(String email);

	User getByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String email);
}
