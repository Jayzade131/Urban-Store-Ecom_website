package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

	List<Review> findAllByProductId(Long productId);

}
