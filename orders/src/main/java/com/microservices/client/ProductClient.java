// ProductClient.java
package com.microservices.client;

import com.microservices.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://host.docker.internal:8082")  // Use host.docker.internal
public interface ProductClient {
    @GetMapping("/api/v1/products/{id}")
    ProductDTO getProductById(@PathVariable Integer id);
}
