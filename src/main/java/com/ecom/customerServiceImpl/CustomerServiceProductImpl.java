package com.ecom.customerServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.customerService.CustomerServiceProduct;
import com.ecom.dto.ProductDetailDto;
import com.ecom.dto.ProductDto;
import com.ecom.entity.FAQ;
import com.ecom.entity.Product;
import com.ecom.entity.Review;
import com.ecom.repo.FAQRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.repo.ReviewRepo;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomerServiceProductImpl implements CustomerServiceProduct {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private FAQRepo faqRepo;
	@Autowired
	private ReviewRepo reviewRepo;
	
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
	
	public ProductDetailDto getProductDetailById(Long productId)
	{
		Optional<Product> optionalProduct = productRepo.findById(productId);
		
		if(optionalProduct.isPresent())
		{
				List<FAQ> faqList = faqRepo.findAllByProductId(productId);
				List<Review> reviewList = reviewRepo.findAllByProductId(productId);
				
				ProductDetailDto productDetailDto=new ProductDetailDto();
				productDetailDto.setProductDto(optionalProduct.get().getDto());
				productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
				productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getReviewDto).collect(Collectors.toList()));
				
				return productDetailDto;
		}
		return null;
	}
}
