package com.ecom.customerService;

import java.util.List;

import com.ecom.dto.WishlistDto;

public interface WishlistService {
	
	public WishlistDto addWishlist(WishlistDto wishlistDto);
	
	public List<WishlistDto> getWishlist(Long userId);
}
