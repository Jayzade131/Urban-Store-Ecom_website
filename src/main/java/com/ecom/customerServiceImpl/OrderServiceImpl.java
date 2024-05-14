package com.ecom.customerServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.constants.OrderStatus;
import com.ecom.customerService.OrderService;
import com.ecom.dto.OrderDto;
import com.ecom.entity.Order;
import com.ecom.repo.OrderRepo;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepo orderRepo;
	
	public List<OrderDto> getplacedOrder(Long userId){
		
	return	orderRepo.findByUsersIdAndOrderStatusIn
			(userId, List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered))
			.stream().map(Order::getOrderDto).collect(Collectors.toList());
		
	}
}
