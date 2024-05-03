package com.ecom.service;

import java.io.IOException;
import java.util.List;

import com.ecom.dto.ProductDto;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto productDto) throws IOException;
	
	public List<ProductDto> getAllProduct();
	
	public List<ProductDto> getAllProductByName(String title);
	
	public boolean deleteProduct(Long id);

}
