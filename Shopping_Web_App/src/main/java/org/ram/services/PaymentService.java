package org.ram.services;

import java.util.Random;

import org.ram.dto.PaymentErrorResponse;
import org.ram.dto.PaymentResponseDTO;
import org.ram.models.Payment;
import org.ram.repositories.OrderRepository;
import org.ram.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private  OrderRepository  orderRepository;

	public PaymentResponseDTO saveAndgetPaymentResponse(Integer userId, Integer orderId, Integer amount) {
	  
		Payment payment=new Payment();
		String tid=generateId();
		payment.setAmount(amount);
		payment.setOrderId(orderId);
		payment.setStatus("successful");
		payment.setUserId(userId);
		payment.setTransactionId(tid);
		paymentRepository.save(payment);
		PaymentResponseDTO paymentResponse = new PaymentResponseDTO();
	        paymentResponse.setUserId(userId);
	        paymentResponse.setOrderId(orderId);
	        paymentResponse.setTransactionId(tid);
	        paymentResponse.setStatus("successful");

		return paymentResponse;
	} 
	
	
	public boolean checkvalidamount(Integer userId,Integer orderId,Integer amount) {
		 Integer oamount=orderRepository.getAmount(userId, orderId);
		 System.out.println(oamount);
		 System.out.println(amount);
		 
			if(amount.equals(oamount)) {
		    	return true;
		    }
			return false;
	 
	}
	

	public static String generateId() {

		int min = 1;
		int max = 15;
	String tid=	"tran01010000";
		Random random = new Random();
		int orderId = random.nextInt(max - min + 1) + min;

		return tid+orderId;
	}


	public PaymentErrorResponse saveAndgetPaymentErrorResponse(Integer userId, Integer orderId, Integer amount) {
		PaymentErrorResponse paymentErrorResponse=new PaymentErrorResponse();
		paymentErrorResponse.setOrderId(orderId);
		paymentErrorResponse.setStatus("Failed");
		paymentErrorResponse.setUserId(userId);
		paymentErrorResponse.setTransactionId(generateId());
		paymentErrorResponse.setDescription("Payment Failed as amount is invalid");
		return paymentErrorResponse;
	}
}
