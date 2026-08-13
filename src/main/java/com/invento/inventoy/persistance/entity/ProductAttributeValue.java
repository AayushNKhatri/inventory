package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute_value")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pav_id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private AttributeDefinition attribute_definition;

    @Column(nullable = true)
    private String values;
}
