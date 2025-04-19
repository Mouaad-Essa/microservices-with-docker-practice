package com.microservices;

import com.microservices.DTO.CustomerDTO;
import com.microservices.DTO.OrderRequestDTO;
import com.microservices.DTO.OrderResponseDTO;
import com.microservices.DTO.ProductDTO;
import com.microservices.client.CustomerClient;
import com.microservices.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        // Map the request to order items
        List<OrderItem> items = request.getOrderItems().stream().map(item ->
                OrderItem.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity().intValue())
                        .build()
        ).collect(Collectors.toList());

        // Create Order object
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .orderItems(items)
                .build();

        // Set order to order items
        items.forEach(i -> i.setOrder(order));

        // Save order to the repository
        Order savedOrder = orderRepository.save(order);

        // Map the saved Order entity to OrderResponseDTO
        return buildOrderResponse(savedOrder);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::buildOrderResponse)
                .collect(Collectors.toList());
    }

    public Optional<OrderResponseDTO> getOrderById(Integer id) {
        return orderRepository.findById(id).map(this::buildOrderResponse);
    }

    public Optional<OrderResponseDTO> updateOrder(Integer id, OrderRequestDTO request) {
        return orderRepository.findById(id).map(order -> {
            List<OrderItem> updatedItems = request.getOrderItems().stream().map(item ->
                    OrderItem.builder()
                            .productId(item.getProductId())
                            .quantity(item.getQuantity().intValue())
                            .order(order)
                            .build()
            ).collect(Collectors.toList());

            order.setCustomerId(request.getCustomerId());
            order.setOrderItems(updatedItems);

            Order updatedOrder = orderRepository.save(order);
            return buildOrderResponse(updatedOrder);
        });
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    private OrderResponseDTO buildOrderResponse(Order order) {
        CustomerDTO customer = customerClient.getCustomerById(order.getCustomerId());
        List<ProductDTO> products = order.getOrderItems().stream()
                .map(item -> productClient.getProductById(item.getProductId()))
                .collect(Collectors.toList());

        return new OrderResponseDTO(order, customer, products);
    }
}
