package com.example.demo.controllers;


import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.Order;
import com.example.demo.services.interfaces.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderById(@PathVariable Long id) {
        List<OrderResponseDTO> order = orderService.getOrderByCustomerId(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public Void createOrder(@RequestBody Order order) {
       orderService.saveOrder(order);
        return null;
    };

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);  // Retorna la orden actualizada con un estado 200 OK
        } else {
            return ResponseEntity.notFound().build();  // Retorna un 404 Not Found si no se encuentra la orden
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);  // Intenta eliminar la orden
            return ResponseEntity.noContent().build();  // Retorna 204 No Content si la eliminación fue exitosa
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 Not Found si no se encuentra la orden
        }
    }
}
