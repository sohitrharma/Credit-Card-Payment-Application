package com.cg.creditcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.exceptions.InvalidPaymentException;
import com.cg.creditcard.exceptions.InvalidTransactionException;
import com.cg.creditcard.exceptions.PaymentNotFoundException;
import com.cg.creditcard.repository.ICreditCardRepository;
import com.cg.creditcard.repository.IPaymentRepository;
import com.cg.creditcard.util.PaymentConstants;


/**
 * This class allows to manage Payment by providing CRUD Payment using Payment ID and Extra End points.
 *  To find the Payment Id
 * {@link #getPaymentById()} can be used. 
 * Then CRUD operations can be called like the following:
{@link #addPayment(Payment)} in order to add a Payment that will be managed by the * singleton instance ;
 */

@Transactional 
@Service
public class PaymentServiceImpl implements IPaymentService 
{
	
	@Autowired
	private IPaymentRepository paymentRepository;
	
	@Autowired
	private ICreditCardRepository creditcardRepository;

	//this method is used to add payment
	@Override
	public Payment addPayment(Long cardId,Payment payment) {

			if (payment == null || cardId==null)
				return null;
			CreditCard creditcard=creditcardRepository.findById(cardId).get();
			Optional<Payment> checking = paymentRepository.findById(payment.getPaymentId());
			if (checking.isPresent())
				throw new InvalidPaymentException("Payment Already Exists");
			try {
				payment.setCreditcard(creditcard);
				paymentRepository.save(payment);
			} catch (Exception e) {
				throw new InvalidPaymentException(PaymentConstants.INVALID_PAYMENT_CONST + e.getMessage());
			}
			return payment;
	}
	
	
	//this method is used to remove payment
	@Override
	public Payment removePayment(Long paymentId) {
		if (paymentId == null)
			return null;
		Optional<Payment> checking = paymentRepository.findById(paymentId);
		if (!checking.isPresent()) {
			throw new PaymentNotFoundException(PaymentConstants.PAYMENT_NOT_FOUND_BY_ID_CONST +paymentId);
		}
		Payment payment = paymentRepository.findById(paymentId).get();
		try {
			paymentRepository.deleteById(paymentId);
		} catch (Exception e) {
			throw new InvalidPaymentException(PaymentConstants.INVALID_PAYMENT_CONST + e.getMessage());
		}

		return payment;
	}

	
	//this method is used to update payment
	@Override
	public Payment updatePayment(Long paymentId, Payment payment) {
		if (paymentId == null)
			return null;
		else {
			Optional<Payment> checking = paymentRepository.findById(paymentId);
			if (!checking.isPresent()) {
				throw new PaymentNotFoundException(PaymentConstants.PAYMENT_NOT_FOUND_BY_ID_CONST + paymentId);
			}else {
				try {
					paymentRepository.save(payment);
				} catch (Exception e) {
					throw new InvalidPaymentException(PaymentConstants.INVALID_PAYMENT_CONST + e.getMessage());
				}
			}
		}
		return payment;
	}

	
	//this method is used to get payment by ID
	@Override
	public Payment getPaymentById(Long paymentId) {
		if (paymentId == null)
			return null;
		Optional<Payment> checking = paymentRepository.findById(paymentId);
		if (!checking.isPresent()) {
			throw new PaymentNotFoundException(PaymentConstants.PAYMENT_NOT_FOUND_BY_ID_CONST + paymentId);
		}
		Payment payment;
		try {
			payment = paymentRepository.findById(paymentId).get();
		} catch (Exception e) {
			throw new InvalidTransactionException(PaymentConstants.INVALID_PAYMENT_CONST + e.getMessage());
		}

		return payment;
	}

	
	//this method is used to get all payment
	@Override
	public List<Payment> getAllPayment() {
		List<Payment> payments=new ArrayList<>();
		for(Payment t:paymentRepository.findAll()) {
			payments.add(t);
		}
		if(payments.isEmpty()) {
			throw new PaymentNotFoundException("No payment present in the database");
		}
		return payments;
	}

	
	//this method is used to get payment by type
//	@Override
//	public List<Payment> getAllPaymentByType(String type) {
//		if ( type == null)
//			return null;
//		
//		List<Payment> payments = new ArrayList<>();
//		paymentRepository.findByType(type).forEach(payments::add);
//		
//		if(payments.size()<1)
//			throw new PaymentNotFoundException("Payment not present");
//		
//		List<Payment> paymentsReturn = new ArrayList<>();
//		for (Payment p : payments ) {
//			paymentsReturn.add(p);
//		}
//		return paymentsReturn;
//	}
	
	
	//this method is used to get payment by less then a specific amount
	@Override
	public List<Payment>findByPaymentAmountLessThan(double paymentAmount) 
	{
		List<Payment> payments = getAllPayment();
		List<Payment> lessThanAmountList= new ArrayList<>();
		for (Payment p : payments)
		{
			if (paymentAmount > p.getPaymentAmount()) {
				lessThanAmountList.add(p);
			}	
		}
		return lessThanAmountList;
	}

	
	//this method is used to get payment by greater then a specific amount
	@Override
	public List<Payment> findByPaymentAmountGreaterThan(double paymentAmount) {
		List<Payment> payments = getAllPayment();
		List<Payment> greaterThanAmountList= new ArrayList<>();
		for (Payment p : payments)
		{
			if (paymentAmount < p.getPaymentAmount() ){
				greaterThanAmountList.add(p);
			}	
		}
		return greaterThanAmountList;
	}




}
