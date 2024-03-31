package org.ram.repositories;

import java.util.List;

import org.ram.dto.OrdersDTO;
import org.ram.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer>  {
	@Query("SELECT new org.ram.dto.OrdersDTO(c.orderId,c.amount,c.date,c.coupon) FROM Order c where c.userId=?1")
	public List<OrdersDTO> getUserOrdersById(Integer userId);


	    @Query("SELECT o FROM Order o WHERE o.userId = :userId AND o.orderId = :orderId")
	    public Order getOrderByIds(@Param("userId") Integer userId, @Param("orderId") Integer orderId);


	    @Query("SELECT o.amount FROM Order o WHERE o.userId = :userId AND o.orderId = :orderId")
	    public Integer getAmount(@Param("userId") Integer userId, @Param("orderId") Integer orderId);

}
