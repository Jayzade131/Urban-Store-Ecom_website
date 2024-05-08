package com.ecom.service;

import java.util.List;

import com.ecom.entity.Coupon;

public interface AdminCouponService {
	public Coupon createCoupon(Coupon coupon);
	
	public List<Coupon> getAllCoupon();

}
