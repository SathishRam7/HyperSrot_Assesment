package org.ram.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.ram.dto.OrderResponseByOrderIdDTO;
import org.ram.dto.OrderResponseDTO;
import org.ram.dto.OrdersDTO;
import org.ram.models.Order;
import org.ram.models.Payment;
import org.ram.models.Product;
import org.ram.repositories.OrderRepository;
import org.ram.repositories.PaymentRepository;
import org.ram.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductRepository productRepository;

	public OrderResponseDTO saveAndGetOrderPrice(Integer userId, Integer quantity, String couponCode) {

		Integer pricePerItem = 100;
		Integer discountPercentage = getDiscountPercentage(couponCode);
		Integer totalPrice = pricePerItem * quantity;
		Integer discountedAmount = totalPrice - discountPercentage * quantity;
		Integer newAvailability = productRepository.getAvailability() - quantity;
		Integer newordered = productRepository.getOrdered() + quantity;
		OrderResponseDTO orderResponse = new OrderResponseDTO();
		int orderId = generateOrderId();
		orderResponse.setOrderId(orderId); // Example order ID
		orderResponse.setUserId(userId);
		orderResponse.setQuantity(quantity);
		orderResponse.setAmount(discountedAmount);
		orderResponse.setCoupon(couponCode);
		Order order = new Order();
		order.setCoupon(couponCode);
		order.setUserId(userId);
		order.setOrderId(orderId);
		order.setQuantity(quantity);
		order.setDate(getDate());
		order.setAmount(discountedAmount);
		productRepository.setAvailability(newAvailability);

		productRepository.setOrdered(newordered);
		orderRepository.save(order);

		return orderResponse;
	}

	private Integer getDiscountPercentage(String couponCode) {

		if ("OFF5".equals(couponCode)) {
			return 5;
		} else if ("OFF10".equals(couponCode)) {
			return 10;
		} else {
			return 0;
		}
	}

	public static int generateOrderId() {

		int minOrderId = 100;
		int maxOrderId = 300;

		Random random = new Random();
		int orderId = random.nextInt(maxOrderId - minOrderId + 1) + minOrderId;

		return orderId;
	}

	public List<OrdersDTO> getUserOrders(Integer userId) {
		return orderRepository.getUserOrdersById(userId);

	}

	public OrderResponseByOrderIdDTO getOrderDetailsById(Integer userId, Integer orderId) {

		Order order = orderRepository.getOrderByIds(userId, orderId);
		Payment payment = paymentRepository.getPaymentByIds(userId, orderId);
		OrderResponseByOrderIdDTO orderResponse = new OrderResponseByOrderIdDTO();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setAmount(order.getAmount());
		orderResponse.setDate(order.getDate());
		orderResponse.setCoupon(order.getCoupon());
		orderResponse.setTransactionId(payment.getTransactionId());
		orderResponse.setStatus(payment.getStatus());

		return orderResponse;
	}

	private String getDate() {
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String formattedDate = now.format(formatter);
		return formattedDate;

	}

}
