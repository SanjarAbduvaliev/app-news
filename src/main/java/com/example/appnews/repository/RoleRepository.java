package com.example.appnews.repository;

import com.example.appnews.entity.Role;
import com.example.appnews.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
