package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {

	List<Wishlist>	findByUsersId(Long userId);

}
