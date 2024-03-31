package org.ram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class OrderResponseByOrderIdDTO {
private Integer orderId;
private Integer amount;
private String coupon;
private String transactionId;
private String date;
private String status;
public OrderResponseByOrderIdDTO(Integer orderId, Integer amount, String coupon, String transactionId, String date,
		String status) {
	super();
	this.orderId = orderId;
	this.amount = amount;
	this.coupon = coupon;
	this.transactionId = transactionId;
	this.date = date;
	this.status = status;
}
	
}
