package org.ram.repositories;

import java.util.List;

import org.ram.dto.CouponDTO;
import org.ram.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>  {
	   @Query("SELECT new org.ram.dto.CouponDTO(c.code,c.discountPercentage) FROM Coupon c")
	    List<CouponDTO> findAllCoupons();
	
}
