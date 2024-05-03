package com.ecom.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.CustomerServiceProduct;
import com.ecom.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {
	@Autowired
	private CustomerServiceProduct customerServiceProduct;
	
	@GetMapping("/allProducts")
	public ResponseEntity<List<ProductDto>> getAllProduct()
	{
		List<ProductDto> allProduct = customerServiceProduct.getAllProduct();
		
		return ResponseEntity.ok(allProduct);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> ProductByName(@PathVariable String name)
	{
		List<ProductDto> allProduct = customerServiceProduct.searchByName(name);
		
		return ResponseEntity.ok(allProduct);
	}
}
