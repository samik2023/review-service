package com.inventory.management.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "order-service", url = "${order.service.url}")
    public interface OrderFeign {
        @GetMapping(value = "/orders/{userName}", produces = APPLICATION_JSON_VALUE)
        String getOrderByUserName(@PathVariable String userName);
    }

