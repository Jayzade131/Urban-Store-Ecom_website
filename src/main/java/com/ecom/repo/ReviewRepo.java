package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
