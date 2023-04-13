package com.cg.creditcard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.Transaction;
import com.cg.creditcard.payloads.BaseResponse;
import com.cg.creditcard.service.ITransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage Transaction module by providing the url to the
 * swagger We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */
@CrossOrigin
@RestController
@RequestMapping("/transaction")
@Api(value = "Transaction Module", description = "Operations on Transaction")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;

	@PostMapping("/add/{userId}")
	@ApiOperation(value = "Add a transaction")
	public ResponseEntity<?> addTransaction(@PathVariable("userId") long userId,@Valid @RequestBody Transaction transaction) {
		transaction = transactionService.addTransaction(userId,transaction);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{tranId}")
	@ApiOperation(value = "Remove a transaction")
	public ResponseEntity<?> deleteTransaction(@PathVariable("tranId") long tranId) {
		Transaction transaction = transactionService.removeTransaction(tranId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/update")
	@ApiOperation(value = "Update a Transaction")
	public ResponseEntity<?> updateTransaction(@Valid @RequestBody Transaction transaction) {
		long tranId = transaction.getTranId();
		transaction = transactionService.updateTransaction(tranId, transaction);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}

	@GetMapping("/get/id/{tranId}")
	@ApiOperation(value = "Search a  Transaction with an ID", response = Transaction.class)
	public ResponseEntity<?> findTransactionById(@PathVariable("tranId") long tranId) {
		Transaction transaction = transactionService.getTransactionById(tranId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall")
	@ApiOperation(value = "Show All Transaction")
	public ResponseEntity<?> getAllTransaction() {
		List<Transaction> transaction = transactionService.getAllTransaction();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/findByCardNo/{cardNo}")
	@ApiOperation(value = "Search a  Transaction with an Card No", response = Transaction.class)
	public ResponseEntity<List<Transaction>> getAllByCardNo(@PathVariable("cardNo") String cardNo) {
		List<Transaction> transactions = transactionService.getAllTransactionByCardNo(cardNo);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transactions);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/last60days")
	@ApiOperation(value = "Show last 60 days transaction", response = Transaction.class)
	public ResponseEntity<?> getAllTransactionForSixtyDays() {

		List<Transaction> transaction = transactionService.getTransactionForLastSixtyDays();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/lessThan/{PaymentAmount}")
	@ApiOperation(value = "Show transaction less than Payment Amount", response = Transaction.class)
	public ResponseEntity<?> findByPaymentAmountLess(@PathVariable("PaymentAmount") double paymentAmount) {

		List<Transaction> transaction = transactionService.findByPaymentAmountLessThan(paymentAmount);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/GreaterThan/{PaymentAmount}")
	@ApiOperation(value = "Show transaction greater than Payment Amount", response = Transaction.class)
	public ResponseEntity<?> findByPaymentAmountGreater(@PathVariable("PaymentAmount") double paymentAmount) {

		List<Transaction> transaction = transactionService.findByPaymentAmountGreaterThan(paymentAmount);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/findByCardNoAndAmount/{cardNo}/{PaymountAmount}")
	@ApiOperation(value = "Show transaction of Credit card with Payment Amount", response = Transaction.class)
	public ResponseEntity<?> findByCardNoAndPaymentAmount(@PathVariable("CardNo") String cardNo,
			@PathVariable("PaymentAmount") double paymentAmount) {

		List<Transaction> transaction = transactionService.findByCardNoAndPaymentAmount(cardNo, paymentAmount);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transaction);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	@GetMapping("/findByUserId/{userId}")
	@ApiOperation(value = "Search all  Transactions with user ID", response = Transaction.class)
	public ResponseEntity<List<Transaction>> getAllByUserId(@PathVariable("userId") Long userId) {
		List<Transaction> transactions = transactionService.findAllTransactionByUserId(userId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transactions);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}
	
	@GetMapping("/findpaymentByUserId/{userId}")
	@ApiOperation(value = "Search all  Payments with user ID", response = Transaction.class)
	public ResponseEntity<List<Payment>> getAllPaymentByUserId(@PathVariable("userId") Long userId) {
		List<Payment> transactions = transactionService.getAllPaymentsByUserId(userId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(transactions);
		return new ResponseEntity<List<Payment>>(transactions, HttpStatus.OK);
	}

}
