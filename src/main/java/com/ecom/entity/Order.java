package com.ecom.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecom.constants.OrderStatus;
import com.ecom.dto.OrderDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private Users users;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="coupon_id", referencedColumnName = "id")
	private Coupon coupon;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItems> cartItems;
	
	public OrderDto getOrderDto()
	{
		OrderDto orderDto=new OrderDto();
		orderDto.setId(id);
		orderDto.setDate(date);
		orderDto.setOrderDesc(orderDesc);
		orderDto.setAmount(amount);
		orderDto.setOrderStatus(orderStatus);
		orderDto.setAddress(address);
		orderDto.setUserName(users.getName());
		orderDto.setTrackingId(trackingId);
		if(coupon !=null)
		{
			orderDto.setCouponName(coupon.getName());
		}
		return orderDto;
	
	}
	
}
