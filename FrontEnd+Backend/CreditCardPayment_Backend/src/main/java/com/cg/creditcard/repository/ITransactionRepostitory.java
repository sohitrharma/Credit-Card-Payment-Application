package com.cg.creditcard.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.Transaction;
import com.cg.creditcard.bean.User;

/**
 * This class is the interface of Repository layer Extending JpaRepository will
 * help to use predefined methods from present in that class "Transaction"
 * argument in the JpaRepository tell which class we have to use for database
 * "Long" argument in the JpaRepository tell which type of dataType is being
 * used in payment class
 */
@Repository
public interface ITransactionRepostitory extends JpaRepository<Transaction, Long> {

	public List<Transaction> findByCardNo(String cardNo);

	public List<Transaction> findByPaymentAmountLessThan(double paymentAmount);

	public List<Transaction> findByPaymentAmountGreaterThan(double paymentAmount);

	public List<Transaction> findByCardNoAndPaymentAmount(String cardNo, double paymentAmount);

	public List<Transaction> findAllTransactionByUser(Long userId);

}
