package com.ecom.controller.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.ReviewService;
import com.ecom.dto.OrderedProductsResponseDto;
import com.ecom.dto.ReviewDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/ordered-product/{orderId}")
	public ResponseEntity<OrderedProductsResponseDto> getOrderedProduct(@PathVariable Long orderId)
	{
		return ResponseEntity.ok(reviewService.getOrderedProductResponse(orderId));
	}
	@PostMapping("/review")
	public ResponseEntity<?> addreview(@ModelAttribute ReviewDto reviewDto) throws IOException
	{
		ReviewDto review = reviewService.addReviews(reviewDto);
		if(review ==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(review);
	}
	 
}
