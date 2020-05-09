package com.webupps.custom.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webupps.custom.app.model.Users;

public interface CheckUserRepository extends JpaRepository<Users, Integer> {
	public Users findByUsername(String username);
}
