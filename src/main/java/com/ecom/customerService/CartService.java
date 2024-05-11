package com.ecom.customerService;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.AddProductInCartDto;
import com.ecom.dto.OrderDto;
import com.ecom.dto.PlaceOrderDto;

public interface CartService {
	
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	public OrderDto getCartByUserId(Long userId);
	
	public OrderDto applyCoupon(Long userId, String code);
	
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	public OrderDto placeOrder(PlaceOrderDto placeOrderDto);
}
