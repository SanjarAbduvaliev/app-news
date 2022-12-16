package com.example.appnews.repository;

import com.example.appnews.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

}
