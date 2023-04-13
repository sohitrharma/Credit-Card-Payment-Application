package com.cg.creditcard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.Transaction;


/**
 * This class is the interface of Transaction layer
*  To find the Transaction Id
 * {@link #getTransactionById()} can be used. 
 * Then CRUD operations can be called like the following:
{@link # addTransaction(Transaction)} in order to add a Transaction that will be managed by the * singleton instance ;
 */


@Service
public interface ITransactionService {

	public Transaction addTransaction(Long userId,Transaction transaction);
	public Transaction removeTransaction(Long tranId);
	public Transaction updateTransaction(Long tranId, Transaction transaction);
	public Transaction getTransactionById(Long tranId);
	public List<Transaction> getAllTransaction(); 
	public List<Transaction>	getAllTransactionByCardNo(String cardNo);
	public List<Transaction> getTransactionForLastSixtyDays();
	public List<Transaction> findByPaymentAmountGreaterThan(double paymentAmount);
	public List<Transaction> findByCardNoAndPaymentAmount(String cardNo, double paymentAmount);
	public List<Transaction> findByPaymentAmountLessThan(double paymentAmount);
	public List<Transaction> findAllTransactionByUserId(Long userId);
	public List<Payment> getAllPaymentsByUserId(Long userId);
}






