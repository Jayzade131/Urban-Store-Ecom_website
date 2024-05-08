package com.ecom.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Coupon;
import com.ecom.exceptions.ValidationException;
import com.ecom.service.AdminCouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCouponController {
	@Autowired
	private AdminCouponService adminCouponService;
	
	@PostMapping("/createCoupon")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon)
	{
		try {
			Coupon createCoupon = adminCouponService.createCoupon(coupon);
			return ResponseEntity.ok(createCoupon);
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("/allCoupon")
	public ResponseEntity<List<Coupon>> getAllCoupon()
	{
		return ResponseEntity.ok(adminCouponService.getAllCoupon());
	}

}
