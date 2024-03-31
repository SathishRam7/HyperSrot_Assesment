package org.ram.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class OrdersDTO {
	private Integer orderId;
	private Integer amount;
	private String date;
	private String coupon;

	public OrdersDTO(Integer orderId, Integer amount, String date, String coupon) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.date = date;
		this.coupon = coupon;
	}

}
