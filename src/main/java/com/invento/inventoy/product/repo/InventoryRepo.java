package com.invento.inventoy.product.repo;

import com.invento.inventoy.persistance.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Inventory findByIdAndIsDeletedFalseAndIsActiveTrue(Long Id);
    List<Inventory> findAllByIsDeletedFalseAndIsActiveTrue();
}
