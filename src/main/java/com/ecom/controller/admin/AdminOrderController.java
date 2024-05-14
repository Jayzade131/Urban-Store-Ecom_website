package com.ecom.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ChangeOrderDto;
import com.ecom.dto.OrderDto;
import com.ecom.service.AdminOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {
	@Autowired
	private AdminOrderService adminOrderService;
	
	@GetMapping("/getplacedorder")
	public ResponseEntity<List<OrderDto>> getAllPlaceOrder()
	{
			return ResponseEntity.ok(adminOrderService.getAllPlacedOrder());
	}
	
	@PostMapping("/orderStatusChange")
	public ResponseEntity<?> changeOrderStatus(@RequestBody ChangeOrderDto changeOrderDto)
	{
		
		OrderDto orderDto = adminOrderService.ChangeOrderStatus(changeOrderDto.getOrderId(),changeOrderDto.getStatus());
		
		if(orderDto==null)
		{
			return new ResponseEntity<>("SomeThing went wrong" ,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
		
	}
	
}
