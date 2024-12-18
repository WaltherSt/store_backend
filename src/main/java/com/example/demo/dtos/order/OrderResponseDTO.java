package com.example.demo.dtos.order;

import com.example.demo.enums.OrderStatus;
import com.example.demo.models.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderResponseDTO {
    Long orderId;
    LocalDateTime date;
    BigDecimal amount;
    OrderStatus status;
    List<OrderDetailDTO> products;

    public OrderResponseDTO(Long orderId, LocalDateTime date, BigDecimal amount, OrderStatus status,
                            List<OrderDetailDTO> products) {
        this.orderId = orderId;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.products = products;
    }

    public OrderResponseDTO() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetailDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderDetailDTO> products) {
        this.products = products;
    }
}
