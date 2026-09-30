package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movement")
@Getter
@NoArgsConstructor
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventory_movement_id;
    private int quantity_change;
    private String reason;
    private String remark;

    @ManyToOne
    @JoinColumn(name="inventory_id", nullable = false)
    private Inventory inventory;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
/**
 * Resason Later have to be provided form the Enum
 * possible enum values
 * STOCK_IN
 * SOLD
 * CUSTOMER_RETURN
 * VENDOR_RETURN
 * DAMAGED_IN
 * DAMAGED_OUT
 * MANUAL_ADJUSTMENT
 * **/