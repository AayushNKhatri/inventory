package com.invento.inventoy.persistance.entity;

import com.invento.inventoy.product.dto.ProductDto;
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
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long Id;

    @Column(nullable = false)
    private String product_name;
    @Column(columnDefinition = "TEXT")
    private String product_description;
    //Make new File Table for image store the metadata sku's, barcode

    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType product_type;

    @OneToMany(mappedBy = "product")
    private List<ProductAttributeValue> product_attribute_value = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    private Inventory inventory;
    @OneToMany(mappedBy = "product")
    private List<OrderItem> order_items = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public void deleteProduct(){
        this.isDeleted = true;
    }
    public void ChangeProductStatus(){
        this.isActive = !this.isActive;
    }
    public Product(String name, String description, ProductType productType){
        this.product_name = name;
        this.product_description = description;
        this.product_type = productType;
    }
    public void UpdateProduct(String name, String description){
        this.product_name = name;
        this.product_description = description;
        this.product_type = new ProductType();
    }
}
