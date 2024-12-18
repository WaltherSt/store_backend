package com.example.demo.dtos.order;

import com.example.demo.models.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailDTO {
    Long detailId;
    Integer quantity;
    BigDecimal price;
    OrderProductDTO product;


    public OrderDetailDTO(OrderProductDTO product, BigDecimal price, Integer quantity, Long detailId) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.detailId = detailId;
    }

    public OrderDetailDTO() {
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderProductDTO getProduct() {
        return product;
    }

    public void setProduct(OrderProductDTO product) {
        this.product = product;
    }
}
