package com.cg.creditcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcard.bean.Payment;



/**
 * This class is the interface of Repository layer
 * Extending JpaRepository will help to use predefined methods from present in that class
 * "Payment" argument in the JpaRepository tell which class we have to use for database
 * "Long" argument in the JpaRepository tell which type of dataType is being used in payment class
 */

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long>{
	
//	public List<Payment> findByType(String type);
	public List<Payment> findByPaymentAmountLessThan(double paymentAmount);
	public List<Payment> findByPaymentAmountGreaterThan(double paymentAmount);
	
	
}
