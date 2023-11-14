package com.inventory.management.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.management.demo.entity.ReviewRecord;
import com.inventory.management.demo.feign.BookFeign;
import com.inventory.management.demo.feign.OrderFeign;
import com.inventory.management.demo.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private OrderFeign orderClient;

    @Autowired
    private BookFeign bookClient;

    public ResponseEntity<String> createReview(ReviewRecord review) {

        //check if user name is present in order service for the specific book id to be allowed
        // to review only
        String orderJson = orderClient.getOrderByUserName(review.getReviewedBy());

        //check if the book to be reviewed is present in book service.
        String bookJson = bookClient.getBookById(String.valueOf(review.getBookId()));

        //If both are present in DB then only review record is allowed
        try {
            Map orderDataMap = new ObjectMapper().readValue(orderJson, HashMap.class);
            Map bookDataMap = new ObjectMapper().readValue(bookJson, HashMap.class);
            String usrName = (String)orderDataMap.get("userName");
            Integer bookId = (Integer)orderDataMap.get("id");
            if(review.getReviewedBy().equalsIgnoreCase(usrName)
               && review.getBookId() == bookId.longValue()) {
                repository.save(review);
                log.info("Review saved successfully");
                return new ResponseEntity<>("Review saved successfully", HttpStatus.OK);
            }
            else{
                log.info("User not allowed to review as no item ordered before");
                return new ResponseEntity<>("Failed to save review", HttpStatus.NOT_FOUND);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
