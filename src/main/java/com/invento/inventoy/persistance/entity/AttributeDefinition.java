package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute_definition")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttributeDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attribute_id;

    @Column(nullable = false)
    private String attribute_definition_name;

    @Column(nullable = false)
    private String attribute_data_type;
    private boolean is_requeried = false;
    private boolean is_deleted = false;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType product_type;

    @OneToMany(mappedBy = "attribute_definition")
    private List<ProductAttributeValue> product_attribute_value = new ArrayList<>();

}
