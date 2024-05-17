package com.ecom.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customerService.WishlistService;
import com.ecom.dto.WishlistDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerWishListController {
	@Autowired
	private WishlistService wishlistService;

	@PostMapping("/wishlist")
	public ResponseEntity<?> addWishList(@RequestBody WishlistDto wishlistDto) {
		WishlistDto addWishlist = wishlistService.addWishlist(wishlistDto);
		if (addWishlist == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong..!");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(addWishlist);
	}
	
		
	@GetMapping("getWishlist/{userId}")
	public ResponseEntity<List<WishlistDto>> getWishlist(@PathVariable Long userId) {
		return ResponseEntity.ok(wishlistService.getWishlist(userId));
	}
}
