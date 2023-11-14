package com.inventory.management.demo.controller;

import com.inventory.management.demo.repository.ReviewRepository;
import com.inventory.management.demo.entity.ReviewRecord;
import com.inventory.management.demo.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping(value ="/reviews", consumes = APPLICATION_JSON_VALUE,
            produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addReview(@RequestBody ReviewRecord record){

        return service.createReview(record);
    }

}
