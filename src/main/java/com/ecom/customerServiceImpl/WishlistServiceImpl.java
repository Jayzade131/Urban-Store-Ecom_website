package com.ecom.customerServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.customerService.WishlistService;
import com.ecom.dto.WishlistDto;
import com.ecom.entity.Product;
import com.ecom.entity.Users;
import com.ecom.entity.Wishlist;
import com.ecom.repo.ProductRepo;
import com.ecom.repo.UserRepo;
import com.ecom.repo.WishlistRepo;
@Service
public class WishlistServiceImpl implements WishlistService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private WishlistRepo wishlistRepo;
	
	public WishlistDto addWishlist(WishlistDto wishlistDto)
	{
		Optional<Product> optionalProduct = productRepo.findById(wishlistDto.getProductId());
		
		Optional<Users> optionalUser = userRepo.findById(wishlistDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Wishlist wishlist=new Wishlist();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUsers(optionalUser.get());
			
			return wishlistRepo.save(wishlist).getWishlistDto();
		}
		return null;
		
		
		
	}
	
	
	public List<WishlistDto> getWishlist(Long userId)
	{
	return	wishlistRepo.findByUsersId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
		
	}
}
