package com.example.demo.services.interfaces;

import com.example.demo.dtos.OrderDTO;
import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    List<OrderResponseDTO> getOrderByCustomerId(Long id);
    Order saveOrder(Order order);
    Order updateOrder(Long id,Order order);
    void deleteOrder(Long id);

}
