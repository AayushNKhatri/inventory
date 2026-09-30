package com.invento.inventoy.ProductType.repo;

import com.invento.inventoy.persistance.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepo extends JpaRepository<ProductType, Long> {
    Optional<ProductType> findByIdAndIsDeletedFalse(Long id);
    List<ProductType> findAllByIsDeletedFalse();
}
