package com.ecom.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.OrderService;
import com.ecom.dto.OrderDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class OrderController {
	@Autowired
	private OrderService orderService;
		
		@GetMapping("/getallorder/{userId}")
		public ResponseEntity<List<OrderDto>> getAllPlacedOrder(@PathVariable Long userId)
		{
			return ResponseEntity.ok(orderService.getplacedOrder(userId));
		}
}
