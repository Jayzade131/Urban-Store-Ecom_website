package com.ecom.dto;

import lombok.Data;

@Data
public class WishlistDto {
	
	private Long id;
	
	private Long userId;
	
	private Long productId;
	
	private String productName;
	
	private String productDesc;
	
	private byte[] returnedImg;
	
	private Long price;
}
