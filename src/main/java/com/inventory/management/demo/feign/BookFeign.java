package com.inventory.management.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "book-service", url = "${book.service.url}")
    public interface BookFeign {
        @GetMapping(value = "/books/{bid}", produces = APPLICATION_JSON_VALUE)
        String getBookById(@PathVariable String bid);
    }

