package com.ecom.customerService;

import com.ecom.dto.OrderedProductsResponseDto;

public interface ReviewService {

	public OrderedProductsResponseDto getOrderedProductResponse(Long orderId);
}
