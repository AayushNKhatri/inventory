package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

}
