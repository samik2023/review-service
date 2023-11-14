package com.inventory.management.demo.repository;

import com.inventory.management.demo.entity.ReviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewRecord, Long> {

}
