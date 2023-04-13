package com.cg.creditcard.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.Transaction;
import com.cg.creditcard.bean.User;

import com.cg.creditcard.exceptions.InvalidTransactionException;
import com.cg.creditcard.exceptions.TransactionNotFoundException;
import com.cg.creditcard.repository.ITransactionRepostitory;
import com.cg.creditcard.repository.IUserRepository;
import com.cg.creditcard.util.CreditCardConstants;
import com.cg.creditcard.util.TransactionConstants;


/**
 * This class allows to manage Transaction by providing CRUD Transaction using Transaction ID and Extra End points.
 *  To find the Transaction Id
 * {@link #getTransactionById()} can be used. 
 * Then CRUD operations can be called like the following:
{@link #addTransaction(Transaction)} in order to add a Transaction that will be managed by the * singleton instance ;
 */

@Transactional 
@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private ITransactionRepostitory transactionRepository;
	
	@Autowired
	private IUserRepository userRepository;

	//this method is to add transactions
	@Override
	public Transaction addTransaction(Long userId,Transaction transaction) {

		if (transaction == null || userId == null)
			throw new InvalidTransactionException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Please enter all the creditcard details with the user id.");
		User user = userRepository.findById(userId).get();

		Optional<Transaction> checking = transactionRepository.findById(transaction.getTranId());
		if (checking.isPresent())
			throw new InvalidTransactionException("CreditCard Already Exists");
		try {
			transaction.setUser(user);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			throw new InvalidTransactionException(TransactionConstants.INVALID_TRANSACTION_CONST + e.getMessage());
		}
		return transaction;
	}
	
	
	
	
	//this method is to remove transactions
	@Override
	public Transaction removeTransaction(Long tranId) {
		if (tranId == null)
			return null;
		Optional<Transaction> checking = transactionRepository.findById(tranId);
		if (!checking.isPresent()) {
			throw new TransactionNotFoundException(TransactionConstants.TRANSACTION_NOT_FOUND_BY_ID_CONST + tranId);
		}
		Transaction transaction = transactionRepository.findById(tranId).get();
		try {
			transactionRepository.deleteById(tranId);
		} catch (Exception e) {
			throw new InvalidTransactionException(TransactionConstants.INVALID_TRANSACTION_CONST + e.getMessage());
		}
		return transaction;
	}

	//this method is used to update transactions
	
	@Override
	public Transaction updateTransaction(Long tranId , Transaction transaction) {
		if (tranId == null)
			return null;
		else {
			Optional<Transaction> checking = transactionRepository.findById(tranId);
			if (!checking.isPresent()) {
				throw new TransactionNotFoundException(TransactionConstants.TRANSACTION_NOT_FOUND_BY_ID_CONST + tranId);
			}else {
				try {
					transactionRepository.save(transaction);
				} catch (Exception e) {
					throw new InvalidTransactionException(TransactionConstants.INVALID_TRANSACTION_CONST + e.getMessage());
				}
			}
		}
		return transaction;
		
	}
	
	
	
	//this method is used to get Transaction by transaction ID
	@Override
	public Transaction getTransactionById(Long tranId) {
		if (tranId == null)
			return null;
		Optional<Transaction> checking = transactionRepository.findById(tranId);
		if (!checking.isPresent()) {
			throw new TransactionNotFoundException(TransactionConstants.TRANSACTION_NOT_FOUND_BY_ID_CONST + tranId);
		}
		Transaction transaction;
		try {
			transaction = transactionRepository.findById(tranId).get();
		} catch (Exception e) {
			throw new InvalidTransactionException(TransactionConstants.INVALID_TRANSACTION_CONST + e.getMessage());
		}

		return transaction;
	}

	
	//this method is used to get all Transaction
	@Override
	public List<Transaction> getAllTransaction() {
		List<Transaction> transactions=new ArrayList<>();
		for(Transaction t:transactionRepository.findAll()) {
			transactions.add(t);
		}
		if(transactions.isEmpty()) {
			throw new TransactionNotFoundException("No Transaction present in the database");
		}
		return transactions;
	}
	
	
	
	//this method is used to get Transaction by CardNO
	@Override
	public List<Transaction> getAllTransactionByCardNo(String cardNo) 
	{
			if (cardNo == null)
				return null;
			
			List<Transaction> transactions = new ArrayList<>();
			transactionRepository.findByCardNo(cardNo).forEach(transactions::add);
			
			if(transactions.size()<1)
				throw new TransactionNotFoundException("Transaction not present");
			
			List<Transaction> transactionsReturn = new ArrayList<>();
			for (Transaction t : transactions ) {
				transactionsReturn.add(t);
			}
			return transactionsReturn;
		}
	
	
	
	
	//this method is used to get Transaction which is done in last 60 days
	@Override 
	public List<Transaction> getTransactionForLastSixtyDays()
	{
		List<Transaction> transactions = getAllTransaction();
		List<Transaction> lastSixtyDays= new ArrayList<>();
		for (Transaction t : transactions)
		{
			long noOfDaysBetween = ChronoUnit.DAYS.between(t.getDate(), LocalDate.now());
			if ((noOfDaysBetween<= 60) && (noOfDaysBetween >=0)) {
				lastSixtyDays.add(t);
			}	
		}
		return lastSixtyDays;
	}

	
	
	//this method is used to get Transaction by less then a specific amount
	@Override
	public List<Transaction> findByPaymentAmountLessThan(double paymentAmount) 
	{
		List<Transaction> transactions = getAllTransaction();
		List<Transaction> lessThanAmountList= new ArrayList<>();
		for (Transaction t : transactions)
		{
			if (paymentAmount > t.getPaymentAmount()) {
				lessThanAmountList.add(t);
			}	
		}
		if(lessThanAmountList.size()<1)
			throw new TransactionNotFoundException("Transaction not present");
		return lessThanAmountList;
	}

	
	
	//this method is used to get Transaction by greater then a specific amount
	@Override
	public List<Transaction> findByPaymentAmountGreaterThan(double paymentAmount) {
		List<Transaction> transactions = getAllTransaction();
		List<Transaction> greaterThanAmountList= new ArrayList<>();
		for (Transaction t : transactions)
		{
			if (paymentAmount <t.getPaymentAmount() ){
				greaterThanAmountList.add(t);
			}	
		}
		if(greaterThanAmountList.size()<1)
			throw new TransactionNotFoundException("Transaction not present");
		return greaterThanAmountList;
	}

	
	//this method is used to get Transaction by card number and specific amount
	@Override
	public List<Transaction> findByCardNoAndPaymentAmount(String cardNo, double paymentAmount) {
		if (cardNo == null || paymentAmount== 0)
			return null;
		
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findByCardNoAndPaymentAmount(cardNo,paymentAmount).forEach(transactions::add);
		
		if(transactions.size()<1)
			throw new TransactionNotFoundException("Transaction not present");
		
		List<Transaction> transactionsReturn = new ArrayList<>();
		for (Transaction t : transactions ) {
			transactionsReturn.add(t);
		}
		return transactionsReturn;
	}




	@Override
	public List<Transaction> findAllTransactionByUserId(Long userId) {
		if (userId == null)
			return null;
		
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findAllTransactionByUser(userId).forEach(transactions::add);
		
		if(transactions.size()<1)
			throw new TransactionNotFoundException("Transaction not present");
		
		List<Transaction> transactionsReturn = new ArrayList<>();
		for (Transaction t : transactions ) {
			transactionsReturn.add(t);
		}
		return transactionsReturn;
	}




	@Override
	public List<Payment> getAllPaymentsByUserId(Long userId) {
		if (userId == null)
			return null;
		List<Transaction> transactions = new ArrayList<>();
		transactionRepository.findAllTransactionByUser(userId).forEach(transactions::add);
		List<Payment> payments=new ArrayList<>();
		for(Transaction t:transactions) {
			payments.add(t.getPayment());
		}
		return payments;
	}


}

	