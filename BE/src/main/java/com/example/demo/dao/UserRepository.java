package com.example.demo.dao;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<Users> findByEmail(String email);
}
