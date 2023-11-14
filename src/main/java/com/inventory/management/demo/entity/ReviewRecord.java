package com.inventory.management.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "REVIEWS_JPA")
public class ReviewRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long bookId;
    private Rating rating;
    private String reviewedBy;
}

enum Rating {
    EXCEELLANT, GOOD, AVERAGE;
}