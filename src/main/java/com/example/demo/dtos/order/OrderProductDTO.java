package com.example.demo.dtos.order;
import java.math.BigDecimal;

public class OrderProductDTO {
    Long productId;
    String productName;
    BigDecimal productPrice;

    public OrderProductDTO(Long productId, String productName, BigDecimal productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public OrderProductDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
