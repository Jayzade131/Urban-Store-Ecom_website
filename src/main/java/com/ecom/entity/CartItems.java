package com.ecom.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecom.dto.CartItemsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long price;
	
	private Long quantity;
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Users users;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	
	public CartItemsDto getCartItemsDto()
	{
		CartItemsDto cartItemsDto=new CartItemsDto();
		cartItemsDto.setId(id);
		cartItemsDto.setPrice(price);
		cartItemsDto.setQuantity(quantity);
		cartItemsDto.setProductId(product.getId());
		cartItemsDto.setUserId(users.getId());
		cartItemsDto.setProductName(product.getName());
		cartItemsDto.setReturnImg(product.getImg());
		return cartItemsDto;
	}
}
