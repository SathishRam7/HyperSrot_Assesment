package org.ram.repositories;

import org.ram.models.Order;
import org.ram.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {


	  @Query("SELECT o FROM Payment o WHERE o.userId = :userId AND o.orderId = :orderId")
	    public Payment getPaymentByIds(@Param("userId") Integer userId, @Param("orderId") Integer orderId);
	
}
