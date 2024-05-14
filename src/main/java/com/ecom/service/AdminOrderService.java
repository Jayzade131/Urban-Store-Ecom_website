package com.ecom.service;

import java.util.List;

import com.ecom.dto.OrderDto;

public interface AdminOrderService {
	
	public List<OrderDto> getAllPlacedOrder();
	
	public OrderDto ChangeOrderStatus(Long orderId, String status);

}
