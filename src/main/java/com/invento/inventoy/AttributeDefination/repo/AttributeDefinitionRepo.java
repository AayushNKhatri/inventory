package com.invento.inventoy.AttributeDefination.repo;

import com.invento.inventoy.persistance.entity.AttributeDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributeDefinitionRepo extends JpaRepository<AttributeDefinition, Long> {
    Optional<AttributeDefinition> findByIdAndIsDeletedFalse(Long id);
    List<AttributeDefinition> findAllByIsDeletedFalse();
}
