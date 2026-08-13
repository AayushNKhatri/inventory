package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;

    @Column(nullable = false)
    private String product_name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String product_description;
    //Make new File Table for image store the metadata sku's, barcode
    private boolean is_active = true;
    private boolean is_deleted = false;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType product_type;

    @OneToMany(mappedBy = "product")
    private List<ProductAttributeValue> product_attribute_value = new ArrayList<>();
}
