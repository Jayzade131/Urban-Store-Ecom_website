package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> {

}
