package com.ecom.customerService;

import java.io.IOException;

import com.ecom.dto.OrderedProductsResponseDto;
import com.ecom.dto.ReviewDto;

public interface ReviewService {

	public OrderedProductsResponseDto getOrderedProductResponse(Long orderId);
	
	public ReviewDto addReviews(ReviewDto reviewDto) throws IOException;
}
