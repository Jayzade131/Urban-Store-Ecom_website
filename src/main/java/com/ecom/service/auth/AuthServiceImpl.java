package com.ecom.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.constants.OrderStatus;
import com.ecom.constants.UserRole;
import com.ecom.dto.SignupRequest;
import com.ecom.dto.UserDto;
import com.ecom.entity.Order;
import com.ecom.entity.Users;
import com.ecom.repo.OrderRepo;
import com.ecom.repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDto createUser(SignupRequest signupRequest)
	{
		Users users=new Users();
		users.setEmail(signupRequest.getEmail());
		users.setName(signupRequest.getName());
		users.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
		users.setRole(UserRole.CUSTOMER);
		Users createUser = userRepo.save(users);
		Order order=new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUsers(createUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepo.save(order);
		
		UserDto userDto=new UserDto();
		userDto.setUserid(createUser.getUserid());
		
		return userDto;
	}
	public Boolean hasUserWithEmail(String email)
	{
		return userRepo.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount() {
	Users adminAccount=	userRepo.findByRole(UserRole.ADMIN);
		if(null == adminAccount)
		{
			Users users=new Users();
			users.setEmail("admin@test.com");
			users.setName("adminSlayer");
			users.setRole(UserRole.ADMIN);
			users.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			userRepo.save(users);
			
		}
	}
}
