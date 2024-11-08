package com.example.orderservice.model;

import org.springframework.data.cassandra.core.mapping.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class Order {

    @PrimaryKey
    private UUID id;

    @Column("user_id")
    private Long userId;

    @Column("item_ids")
    private List<String> itemIds;

    @Column("quantities")
    private List<Integer> quantities;

    @Column("total_price")
    private BigDecimal totalPrice;

    @Column("status")
    private String status;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    // Additional fields as needed
}
