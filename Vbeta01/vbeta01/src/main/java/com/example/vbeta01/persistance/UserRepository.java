package com.example.vbeta01.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vbeta01.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
