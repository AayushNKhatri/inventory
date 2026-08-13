package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_type_id;

    @Column(nullable = false)
    private String product_type_name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "product_type")
    private List<AttributeDefinition> attribute_definition = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private  List<Product> product = new ArrayList<>();
}
