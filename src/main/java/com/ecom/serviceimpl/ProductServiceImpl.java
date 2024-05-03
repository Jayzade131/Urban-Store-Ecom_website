package com.ecom.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.ProductDto;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.repo.CategoryRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	@Override
	public ProductDto addProduct(ProductDto productDto) throws IOException {
			Product product=new Product();
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			product.setDescription(productDto.getDescription());
			product.setImg(productDto.getImg().getBytes());
			
			Category category=categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
			
			product.setCategory(category);
			
		return productRepo.save(product).getDto();
	}
	@Override
		public List<ProductDto> getAllProduct()
		{
			List<Product> allProducts = productRepo.findAll();
			
			return allProducts.stream().map(Product::getDto).collect(Collectors.toList());
		}

		@Override
		public List<ProductDto> getAllProductByName(String name) {
			List<Product> allProducts = productRepo.findAllByNameContaining(name);
			System.out.println("product by name " +allProducts);
			return allProducts.stream().map(Product::getDto).collect(Collectors.toList());
		}
		@Override
		public boolean deleteProduct(Long id)
		{
			Optional<Product> product = productRepo.findById(id);
			if(product.isPresent())
			{
					productRepo.deleteById(id);
					return true;
			}
			return false;
		}

}
