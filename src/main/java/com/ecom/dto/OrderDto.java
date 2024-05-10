package com.ecom.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecom.constants.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {
	
private Long id;
	
	private Date date;
	
	private String orderDesc;
	
	private Long amount;
	
	private String address;
	
	private String payment;
	
	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	
	private UUID trackingId;
	
	
	private String userName;
	
	private List<CartItemsDto> cartItems;
	
	private String couponName;

}
