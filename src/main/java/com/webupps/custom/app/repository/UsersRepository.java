package com.webupps.custom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webupps.custom.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findByName(String name);
    List<Users> findByUsername(String username);
   // public Users findByUsername(String username);

}
