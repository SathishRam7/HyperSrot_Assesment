package org.ram.controller;

import java.util.List;
import java.util.Map;

import org.ram.dto.ErrorResponse;
import org.ram.dto.InventoryDTO;
import org.ram.dto.OrderResponseByOrderIdDTO;

import org.ram.dto.OrdersDTO;

import org.ram.services.CouponService;
import org.ram.services.OrderService;
import org.ram.services.PaymentService;
import org.ram.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PaymentService paymentservice;

	@GetMapping("/fetchCoupons")
	public ResponseEntity<Map<String, Integer>> getAllCoupons() {

		return new ResponseEntity<>(couponService.getAllCoupons(), HttpStatus.OK);
	}

	@GetMapping("/inventory")
	public ResponseEntity<InventoryDTO> getinventory() {

		return new ResponseEntity<>(productService.getInventory(), HttpStatus.OK);
	}

	@PostMapping("/{userId}/order")
	public ResponseEntity<?> createOrder(@PathVariable("userId") Integer userId, @RequestParam("qty") Integer quantity,
			@RequestParam("coupon") String couponCode) {

		if (quantity < 1 || quantity > 100) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Invalid quantity"));
		}

		if (!isValidCoupon(couponCode)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Invalid coupon"));
		}

		return new ResponseEntity<>(orderService.saveAndGetOrderPrice(userId, quantity, couponCode), HttpStatus.OK);
	}

	@PostMapping("/{userId}/{orderId}/pay")
	public ResponseEntity<?> makePayment(@PathVariable Integer userId, @PathVariable Integer orderId,
			@RequestParam Integer amount) {

		if (paymentservice.checkvalidamount(userId, orderId, amount)) {
			return new ResponseEntity<>(paymentservice.saveAndgetPaymentResponse(userId, orderId, amount),
					HttpStatus.OK);

		} else {
			return new ResponseEntity<>(paymentservice.saveAndgetPaymentErrorResponse(userId, orderId, amount),
					HttpStatus.OK);
		}
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<OrdersDTO>> getUserOrders(@PathVariable Integer userId) {
		List<OrdersDTO> orders = orderService.getUserOrders(userId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{userId}/orders/{orderId}")
	public ResponseEntity<OrderResponseByOrderIdDTO> getOrderDetails(@PathVariable Integer userId,
			@PathVariable Integer orderId) {

		return new ResponseEntity<>(orderService.getOrderDetailsById(userId, orderId), HttpStatus.OK);
	}

	private boolean isValidCoupon(String couponCode) {

		return couponCode.equals("OFF5") || couponCode.equals("OFF10");
	}

}
