package com.ecom.customerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.customerService.CartService;
import com.ecom.repo.CartItemRepo;
import com.ecom.repo.OrderRepo;
import com.ecom.repo.UserRepo;

public class CartServiceImpl implements CartService {
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CartItemRepo cartItemRepo;
}
