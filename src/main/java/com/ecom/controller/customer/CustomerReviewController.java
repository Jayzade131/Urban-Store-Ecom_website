package com.ecom.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.ReviewService;
import com.ecom.dto.OrderedProductsResponseDto;

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
}
