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

    @Column(name = "status")
    private String status;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(nullable = false, name = "total_amt")
    private BigDecimal totalAmt;
    @Column(name = "remark")
    private String remark;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_item = new ArrayList<>();

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
}
