package com.microservices.DTO;

import com.microservices.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Order order;
    private CustomerDTO customer;
    private List<ProductDTO> products;
}
