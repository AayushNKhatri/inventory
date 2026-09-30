package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attribute_definition")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttributeDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="attribute_id")
    private Long id;

    @Column(nullable = false, name="attribute_definition_name")
    private String attributeDefinitionName;

    @Column(nullable = false, name="attribute_data_type")
    private String attributeDataType;

    @Column(name="is_requeried")
    private boolean isRequeried = false;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType product_type;

    @OneToMany(mappedBy = "attribute_definition")
    private List<ProductAttributeValue> product_attribute_value = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public void deleteUser(){
        this.isDeleted = true;
    }
    public AttributeDefinition(String name, String DataType, boolean requeried, ProductType productType){
        this.attributeDefinitionName= name;
        this.attributeDataType = DataType;
        this.isRequeried = requeried;
        this.product_type = productType;
    }
    public void Update(String name, String DataType, boolean requeried){
        this.attributeDefinitionName = name;
        this.attributeDataType = DataType;
        this.isRequeried = requeried;
    }
}
