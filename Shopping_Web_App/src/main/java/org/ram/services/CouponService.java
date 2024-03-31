package org.ram.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ram.dto.CouponDTO;
import org.ram.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CouponService {
	
	@Autowired
private  CouponRepository  couponRepository;


public Map<String, Integer> getAllCoupons() {
    List<CouponDTO> coupons = couponRepository.findAllCoupons();

    Map<String, Integer> couponMap = new HashMap<>();
    for (CouponDTO coupon : coupons) {
        couponMap.put(coupon.getCode(), coupon.getDiscountPercentage());
    }

    return couponMap;
}
}