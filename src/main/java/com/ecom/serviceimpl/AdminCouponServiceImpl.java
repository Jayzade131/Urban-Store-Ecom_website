package com.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Coupon;
import com.ecom.exceptions.ValidationException;
import com.ecom.repo.CouponRepo;
import com.ecom.service.AdminCouponService;
@Service
public class AdminCouponServiceImpl implements AdminCouponService {
	@Autowired
	private CouponRepo couponRepo;
	
	public Coupon createCoupon(Coupon coupon)
	{
		if(couponRepo.existsByCode(coupon.getCode()))
		{
			throw new ValidationException("Coupon code already exist.");
			
		}
		return couponRepo.save(coupon);
	}
	
	public List<Coupon> getAllCoupon()
	{
		List<Coupon> findAll = couponRepo.findAll();
		return findAll;
	}

}
