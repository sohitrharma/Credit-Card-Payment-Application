package com.cg.creditcard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.Payment;

/**
 * This class is the interface of Service layer
*  To find the Payment Id
 * {@link #getPaymentById()} can be used. 
 * Then CRUD operations can be called like the following:
{@link # addPayment(Payment)} in order to add a Payment that will be managed by the * singleton instance ;
 */



@Service
public interface IPaymentService {
	
	public Payment addPayment(Long cardId,Payment payment);
	public Payment removePayment(Long paymentId);
	public Payment updatePayment(Long paymentId, Payment payment);
	public Payment getPaymentById(Long paymentId);
	public List<Payment> getAllPayment(); 
//	public List<Payment>	getAllPaymentByType(String type);
	public List<Payment> findByPaymentAmountLessThan(double paymentAmount);
	public List<Payment> findByPaymentAmountGreaterThan(double paymentAmount);
}