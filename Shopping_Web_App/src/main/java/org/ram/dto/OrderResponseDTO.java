package org.ram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
	   private Integer orderId;
	    private Integer userId;
	    private Integer quantity;
	    private Integer amount;
	    private String coupon;
}
