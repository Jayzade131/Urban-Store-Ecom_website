package com.ecom.customerService;

import java.util.List;

import com.ecom.dto.ProductDetailDto;
import com.ecom.dto.ProductDto;

public interface CustomerServiceProduct {
	
public List<ProductDto> getAllProduct();
	
	public List<ProductDto> searchByName(String title);
	
	public ProductDetailDto getProductDetailById(Long productId);

}
