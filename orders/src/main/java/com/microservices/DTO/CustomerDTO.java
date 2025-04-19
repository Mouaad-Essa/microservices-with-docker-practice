package com.microservices.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;
    private String name;
    private String email;
}