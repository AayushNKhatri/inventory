package com.invento.inventoy.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invento.inventoy.persistance.entity.Inventory;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Inventory findByIdAndIsDeletedFalse(Long Id);
    List<Inventory> findAllByAndIsDeletedFalse();
}
