package com.ecom.customerServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.customerService.ReviewService;
import com.ecom.dto.OrderedProductsResponseDto;
import com.ecom.dto.ProductDto;
import com.ecom.dto.ReviewDto;
import com.ecom.entity.CartItems;
import com.ecom.entity.Order;
import com.ecom.entity.Product;
import com.ecom.entity.Review;
import com.ecom.entity.Users;
import com.ecom.repo.OrderRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.repo.ReviewRepo;
import com.ecom.repo.UserRepo;
@Service
public class ReviewServiceImpl  implements ReviewService  {
	
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ReviewRepo reviewRepo;
	
	public OrderedProductsResponseDto getOrderedProductResponse(Long orderId)
	{
		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		OrderedProductsResponseDto dto=new OrderedProductsResponseDto();
		if(optionalOrder.isPresent())
		{
			dto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> list=new ArrayList<>();
			for (CartItems cartItems : optionalOrder.get().getCartItems()) {
				ProductDto productDto=new ProductDto();
				productDto.setName(cartItems.getProduct().getName());
				productDto.setId(cartItems.getProduct().getId());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				
				productDto.setByteimg(cartItems.getProduct().getImg());
				
				list.add(productDto);
			}
			
			dto.setProductDtoList(list);
		}
		return dto;
	}
	
	public ReviewDto addReviews(ReviewDto reviewDto) throws IOException
	{
		Optional<Product> optionalProduct = this.productRepo.findById(reviewDto.getProductId());
		
		Optional<Users> optionalUser = this.userRepo.findById(reviewDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
				{
			Review review=new Review();
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setImg(reviewDto.getImg().getBytes());
			review.setUsers(optionalUser.get());
			review.setProduct(optionalProduct.get());
			
			return reviewRepo.save(review).getReviewDto();
				}
		return null;
		
		
	}
}
