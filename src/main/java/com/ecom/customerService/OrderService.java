package com.ecom.customerService;

import java.util.List;

import com.ecom.dto.OrderDto;

public interface OrderService {
	public List<OrderDto> getplacedOrder(Long userId);
}
