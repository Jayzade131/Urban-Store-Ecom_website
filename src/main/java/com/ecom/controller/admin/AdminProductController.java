package com.ecom.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.ProductDto;
import com.ecom.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
			
		@PostMapping("/product")
		public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException
		{
			ProductDto addProduct = productService.addProduct(productDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(addProduct);
		}
		
		@GetMapping("/allProducts")
		public ResponseEntity<List<ProductDto>> getAllProduct()
		{
			List<ProductDto> allProduct = productService.getAllProduct();
			
			return ResponseEntity.ok(allProduct);
		}
		
		@GetMapping("/search/{name}")
		public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name)
		{
			List<ProductDto> allProduct = productService.getAllProductByName(name);
			
			return ResponseEntity.ok(allProduct);
		}
		@DeleteMapping("/deleteProd/{id}")
		public ResponseEntity<Void> deleteProductById(@PathVariable Long id)
		{
			 boolean deleteProduct = productService.deleteProduct(id);
			 if(deleteProduct) {
			 return ResponseEntity.noContent().build();
			 }
			 return ResponseEntity.notFound().build();
		}
		
}
