package com.ecom.customerServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.customerService.CustomerServiceProduct;
import com.ecom.dto.ProductDto;
import com.ecom.entity.Product;
import com.ecom.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomerServiceProductImpl implements CustomerServiceProduct {
	@Autowired
	private ProductRepo productRepo;

	@Override
	public List<ProductDto> getAllProduct()
	{
		List<Product> allProducts = productRepo.findAll();
		
		return allProducts.stream().map(Product::getDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> searchByName(String name) {
		List<Product> allProducts = productRepo.findAllByNameContaining(name);
		System.out.println("product by name " +allProducts);
		return allProducts.stream().map(Product::getDto).collect(Collectors.toList());
	}
}
