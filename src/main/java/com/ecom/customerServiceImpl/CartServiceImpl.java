package com.ecom.customerServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.constants.OrderStatus;
import com.ecom.customerService.CartService;
import com.ecom.dto.AddProductInCartDto;
import com.ecom.dto.CartItemsDto;
import com.ecom.dto.OrderDto;
import com.ecom.entity.CartItems;
import com.ecom.entity.Coupon;
import com.ecom.entity.Order;
import com.ecom.entity.Product;
import com.ecom.entity.Users;
import com.ecom.exceptions.ValidationException;
import com.ecom.repo.CartItemRepo;
import com.ecom.repo.CouponRepo;
import com.ecom.repo.OrderRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.repo.UserRepo;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartItemRepo cartItemRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CouponRepo couponRepo;
	
	@Override
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto)
	{
		System.out.println(addProductInCartDto);
	Order activeOrder=	orderRepo.findByUsersIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
	
		Optional<CartItems> optionalCartItems=cartItemRepo.findByProductIdAndOrderIdAndUsersId(addProductInCartDto.getProductId(),
				activeOrder.getId(),addProductInCartDto.getUserId());
		if(optionalCartItems.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		else {
			Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());
			Optional<Users> optionalUsers = userRepo.findById(addProductInCartDto.getUserId());
			if(optionalProduct.isPresent() && optionalUsers.isPresent())
			{
					CartItems cart=new CartItems();
					cart.setProduct(optionalProduct.get());
					cart.setPrice(optionalProduct.get().getPrice());
					cart.setQuantity(1L);
					cart.setUsers(optionalUsers.get());
					cart.setOrder(activeOrder);
					
					CartItems updateCart = cartItemRepo.save(cart);
					
					activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
					activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
					activeOrder.getCartItems().add(cart);
					
					orderRepo.save(activeOrder);	
					
					return ResponseEntity.status(HttpStatus.CREATED).body(cart);
					
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User and product not found");
			}
			
		}
		
	}
	
	public OrderDto getCartByUserId(Long userId)
	{
		Order activeOrder=	orderRepo.findByUsersIdAndOrderStatus(userId, OrderStatus.Pending);
		List<CartItemsDto> cartItemsDtos= activeOrder.getCartItems().stream().map(CartItems :: getCartItemsDto).collect(Collectors.toList()); 
		OrderDto orderDto=new OrderDto();
		
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());
		orderDto.setCartItems(cartItemsDtos);
		
		if(activeOrder.getCoupon() !=null)
		{
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
		return orderDto;
	}
	
	public OrderDto applyCoupon(Long userId, String code)
	{
		Order activeOrder=	orderRepo.findByUsersIdAndOrderStatus(userId, OrderStatus.Pending);
	Coupon coupon =	couponRepo.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not Found"));
		
		if(couponIsExpire(coupon))
		{
			throw new ValidationException("Coupon is Expire");
		}
		double discountAmount = (coupon.getDiscount()/100.0) * activeOrder.getTotalAmount();
		double netAmount = activeOrder.getTotalAmount() - discountAmount;
		
			activeOrder.setAmount((long)netAmount);
			activeOrder.setDiscount((long)discountAmount);
			
			activeOrder.setCoupon(coupon);
			
			orderRepo.save(activeOrder);
			return activeOrder.getOrderDto();
			
		
		
	}
	
	private boolean couponIsExpire(Coupon coupon)
	{
		Date currentDate=new Date();
		Date expirationDate = coupon.getExpirationDate();
		
		return expirationDate !=null && currentDate.after(expirationDate);
	}
	
	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder=	orderRepo.findByUsersIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
		Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());
		
		Optional<CartItems> optionalCartItems=cartItemRepo.findByProductIdAndOrderIdAndUsersId(addProductInCartDto.getProductId(),
				activeOrder.getId(),addProductInCartDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItems.isPresent())	
		{
			CartItems cartItems=optionalCartItems.get();
			Product product=optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());
			cartItems.setQuantity(cartItems.getQuantity() + 1);
			
			if(activeOrder.getCoupon() != null) 
			{
				double discountAmount = (activeOrder.getCoupon().getDiscount()/100.0) * activeOrder.getTotalAmount();
				double netAmount = activeOrder.getTotalAmount() - discountAmount;
				
					activeOrder.setAmount((long)netAmount);
					activeOrder.setDiscount((long)discountAmount);
			}
			cartItemRepo.save(cartItems);
			orderRepo.save(activeOrder);
			return activeOrder.getOrderDto();
		}
		return null;
		
	}
	
	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder=	orderRepo.findByUsersIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
		Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());
		
		Optional<CartItems> optionalCartItems=cartItemRepo.findByProductIdAndOrderIdAndUsersId(addProductInCartDto.getProductId(),
				activeOrder.getId(),addProductInCartDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalCartItems.isPresent())	
		{
			CartItems cartItems=optionalCartItems.get();
			Product product=optionalProduct.get();
			
			activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
			activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
			cartItems.setQuantity(cartItems.getQuantity() - 1);
			
			if(activeOrder.getCoupon() != null) 
			{
				double discountAmount = (activeOrder.getCoupon().getDiscount()/100.0) * activeOrder.getTotalAmount();
				double netAmount = activeOrder.getTotalAmount() - discountAmount;
				
					activeOrder.setAmount((long)netAmount);
					activeOrder.setDiscount((long)discountAmount);
			}
			cartItemRepo.save(cartItems);
			orderRepo.save(activeOrder);
			return activeOrder.getOrderDto();
		}
		return null;
	}
	
}
