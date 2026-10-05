package com.invento.inventoy.product.repo;

import com.invento.inventoy.persistance.entity.Inventory;
import com.invento.inventoy.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
//    Optional<Inventory> findByIdAndIsDeletedFalseAndIsActiveTrue(Long Id);
//    List<Inventory> findAllByIsDeletedFalseAndIsActiveTrue();
}
