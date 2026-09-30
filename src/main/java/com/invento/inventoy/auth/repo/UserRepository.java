package com.invento.inventoy.auth.repo;

import com.invento.inventoy.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
