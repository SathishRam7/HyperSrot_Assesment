package org.ram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
	   private Integer userId;
	    private Integer orderId;
	    private String transactionId;
	    private String status;

}
