// CustomerClient.java
package com.microservices.client;

import com.microservices.DTO.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "http://host.docker.internal:8081")  // Use host.docker.internal
public interface CustomerClient {
    @GetMapping("/api/v1/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable Integer id);
}
