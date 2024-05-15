package com.ecom.dto;

import lombok.Data;

@Data
public class FAQDto {
	
    private Long id;
	
	private String que;
	
	private String ans;
	
	private Long productId;
}
