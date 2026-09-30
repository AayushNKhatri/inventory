package com.invento.inventoy.persistance.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;

    @Column(nullable = false)
    private String customer_name;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    private String status;
    private String payment_status;
    @Column(nullable = false)
    private BigDecimal total_amt;
    private String remark;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_item = new ArrayList<>();

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
