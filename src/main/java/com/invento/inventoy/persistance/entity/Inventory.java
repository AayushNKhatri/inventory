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
    @Column(name = "inventory_id")
    private Long Id;

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

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    public void delete(){
        this.isDeleted = true;
    }
    public Inventory(Product product, int damaged_stock, int sellable_stock, BigDecimal purchase_price, BigDecimal selling_price) {
        this.product = product;
        this.damaged_stock = damaged_stock;
        this.sellable_stock = sellable_stock;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
    }
    public void UpdateInventory(Product product, BigDecimal purchase_price, BigDecimal selling_price) {
        this.product = product;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
    }

}
