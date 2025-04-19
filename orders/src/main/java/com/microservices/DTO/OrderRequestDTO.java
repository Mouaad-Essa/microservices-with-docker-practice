package com.microservices.DTO;

import com.microservices.OrderItemRequest;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private Integer customerId;
    private List<OrderItemRequest> orderItems;
}