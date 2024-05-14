package com.ecom.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.constants.OrderStatus;
import com.ecom.dto.OrderDto;
import com.ecom.entity.Order;
import com.ecom.repo.OrderRepo;
import com.ecom.service.AdminOrderService;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	public List<OrderDto> getAllPlacedOrder()
	{
		List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Delivered,OrderStatus.Shipped));
		
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
		 
	}
	
	public OrderDto ChangeOrderStatus(Long orderId, String status)
	{
		Optional<Order> activeOrder = orderRepo.findById(orderId);
		if(activeOrder.isPresent())
		{
			Order order=activeOrder.get();
			if(Objects.equals(status, "Shipped"))
			{
				order.setOrderStatus(OrderStatus.Shipped);
			}
			else if (Objects.equals(status, "Delivered")) {
				order.setOrderStatus(OrderStatus.Delivered);
			}
			return orderRepo.save(order).getOrderDto();
		}
		return null;
	}

}
