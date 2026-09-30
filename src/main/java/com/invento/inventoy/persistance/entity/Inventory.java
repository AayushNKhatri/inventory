package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "inventory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventory_id;

    @Column(nullable = true)
    private BigDecimal purchase_price;

    @Column(nullable = true)
    private BigDecimal selling_price;

    @Column(nullable = true)
    private int sellable_stock;

    @Column(nullable = true)
    private int damaged_stock;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;
    @OneToMany(mappedBy = "inventory")
    private List<InventoryMovement> inventory_movement;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private boolean is_deleted = false;
    public void deleteUser(boolean is_deleted){
        this.is_deleted = is_deleted;
    }

}
