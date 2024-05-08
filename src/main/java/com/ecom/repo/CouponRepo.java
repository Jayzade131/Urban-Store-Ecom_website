package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
	
	boolean existsByCode(String code);
		
}
