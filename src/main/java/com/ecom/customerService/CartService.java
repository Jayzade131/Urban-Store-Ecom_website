package com.ecom.customerService;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.AddProductInCartDto;
import com.ecom.dto.OrderDto;

public interface CartService {
	
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	public OrderDto getCartByUserId(Long userId);
	
	public OrderDto applyCoupon(Long userId, String code);
}
