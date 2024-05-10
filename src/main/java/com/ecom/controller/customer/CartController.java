package com.ecom.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.CartService;
import com.ecom.dto.AddProductInCartDto;
import com.ecom.dto.OrderDto;
import com.ecom.exceptions.ValidationException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {
		@Autowired
		private CartService cartService;
		
		@PostMapping("/cart")
		public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
			
			return  cartService.addProductToCart(addProductInCartDto);
			
		}
		
		@GetMapping("/cart/{userId}")
		public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
			
			OrderDto orderDto = cartService.getCartByUserId(userId);
			
			return ResponseEntity.status(HttpStatus.OK).body(orderDto);
		}
		
		@GetMapping("/coupon/{userId}/{code}")
		public ResponseEntity<?> applyCoupon(@PathVariable Long userId,@PathVariable String code)
		{
			try {
				OrderDto applyCoupon = cartService.applyCoupon(userId, code);
				return ResponseEntity.ok(applyCoupon);
			} catch (ValidationException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
}
