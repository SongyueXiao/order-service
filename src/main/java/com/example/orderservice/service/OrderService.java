package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(UUID id, Order order);
    Order cancelOrder(UUID id);
    Order getOrderById(UUID id);
    List<Order> getOrdersByUserId(Long userId);
    // Additional methods as needed
}
