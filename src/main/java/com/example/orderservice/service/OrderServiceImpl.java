package com.example.orderservice.service;

import com.example.orderservice.messaging.OrderProducer;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    // Constructor Injection
    public OrderServiceImpl(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @Override
    public Order createOrder(Order order) {
        order.setId(UUID.randomUUID());
        order.setStatus("Created");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        orderProducer.sendOrderEvent(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(UUID id, Order order) {
        Order existingOrder = getOrderById(id);
        existingOrder.setItemIds(order.getItemIds());
        existingOrder.setQuantities(order.getQuantities());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(existingOrder);
    }

    @Override
    public Order cancelOrder(UUID id) {
        Order existingOrder = getOrderById(id);
        existingOrder.setStatus("Canceled");
        existingOrder.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(existingOrder);
    }

    @Override
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        // Implement custom query if necessary
        return new ArrayList<>(); // Placeholder
    }
}
