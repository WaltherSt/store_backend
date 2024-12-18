package com.example.demo.services;

import com.example.demo.dtos.order.OrderDetailDTO;
import com.example.demo.dtos.order.OrderProductDTO;
import com.example.demo.dtos.order.OrderResponseDTO;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();

    }

    @Override
    public List<OrderResponseDTO> getOrderByCustomerId(Long id) {
        List<OrderResponseDTO> orders = new ArrayList<>();

        List<Order> ordersByCustomer = this.orderRepository.findByCustomerId(id);

        ordersByCustomer.forEach(order -> {
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            List<OrderDetailDTO> productDTOList = new ArrayList<>();

            orderResponseDTO.setOrderId(order.getId());
            orderResponseDTO.setAmount(order.getOrderAmount());
            orderResponseDTO.setStatus(order.getStatus());
            orderResponseDTO.setDate(order.getOrderDate());

            order.getOrderDetails().forEach(orderDetail -> {
                OrderDetailDTO detail = new OrderDetailDTO();
                detail.setDetailId(orderDetail.getId());
                detail.setPrice(orderDetail.getPrice());
                detail.setQuantity(orderDetail.getQuantity());

                Product p = orderDetail.getProduct();

                // Crear una nueva instancia de OrderProductDTO para cada producto
                OrderProductDTO productDTO = new OrderProductDTO();
                productDTO.setProductId(p.getId());
                productDTO.setProductName(p.getName());
                productDTO.setProductPrice(p.getPrice());

                // Establecer el producto dentro de OrderDetailDTO
                detail.setProduct(productDTO);

                // Agregar el detalle de la orden a la lista
                productDTOList.add(detail);
            });

            // Asignar la lista de productos al DTO de respuesta
            orderResponseDTO.setProducts(productDTOList);
            orders.add(orderResponseDTO);
        });

        return orders;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        // Usar un arreglo para que el valor sea mutable
        final BigDecimal[] total = { BigDecimal.ZERO };
        order.getOrderDetails().forEach(detail -> {
            // Multiplicar precio por cantidad y sumar al total
            BigDecimal subtotal = detail.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity()));
            total[0] = total[0].add(subtotal); // Actualizar el valor del arreglo
        });
        // Asignar el monto total calculado a la orden
        order.setOrderAmount(total[0]);

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        order.setId(id);
        return this.orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);

    }
}
