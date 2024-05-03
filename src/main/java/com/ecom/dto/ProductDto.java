package com.ecom.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {
	
    private Long id;
	
	private String name;
	
	private Long price;
	
	private String description;
	
	private byte[] byteimg;
	
	private Long categoryId;
	
	private String categoryName;
	
	private MultipartFile img;
}
