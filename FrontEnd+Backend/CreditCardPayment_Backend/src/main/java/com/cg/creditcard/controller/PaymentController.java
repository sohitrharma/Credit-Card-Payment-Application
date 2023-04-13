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
import com.cg.creditcard.payloads.BaseResponse;
import com.cg.creditcard.service.IPaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage payment module by providing the url to the
 * swagger We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */
@CrossOrigin
@RestController
@RequestMapping("/payment")
@Api(value = "Payment Module", description = "Operations on Payment")
public class PaymentController {

	@Autowired
	IPaymentService paymentService;

	@PostMapping("/add/{cardId}")
	@ApiOperation(value = "Add a Payment")
	public ResponseEntity<?> addPayment(@PathVariable("cardId") long cardId,@Valid @RequestBody Payment payment) {
		payment = paymentService.addPayment(cardId,payment);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payment);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{paymentId}")
	@ApiOperation(value = "Remove a Payment")
	public ResponseEntity<?> deletePayment(@PathVariable("paymentId") long paymentId) {
		Payment payment = paymentService.removePayment(paymentId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payment);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/update")
	@ApiOperation(value = "Update a Payment")
	public ResponseEntity<?> updatePayment(@Valid @RequestBody Payment payment) {
		long tranId = payment.getPaymentId();
		payment = paymentService.updatePayment(tranId, payment);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payment);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}

	@GetMapping("/get/id/{paymentId}")
	@ApiOperation(value = "Search a  Payment with an ID", response = Payment.class)
	public ResponseEntity<?> findPaymentById(@PathVariable("paymentId") long paymentId) {
		Payment payment = paymentService.getPaymentById(paymentId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payment);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall")
	@ApiOperation(value = "Show All Payment")
	public ResponseEntity<?> getAllPayment() {
		List<Payment> payment = paymentService.getAllPayment();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payment);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

//	@GetMapping("/findBy/{type}")
//	@ApiOperation(value = "Search a Payment with an type", response = Payment.class)
//	public ResponseEntity<List<Payment>> getAllByType(@PathVariable("type") String type) {
//		List<Payment> payments = paymentService.getAllPaymentByType(type);
//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setStatusCode(1);
//		baseResponse.setResponse(payments);
//		return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
//	}

	@GetMapping("/lessThan/{PaymentAmount}")
	@ApiOperation(value = "Show Payment less than Payment Amount", response = Payment.class)
	public ResponseEntity<?> findByPaymentAmountLess(@PathVariable("PaymentAmount") double paymentAmount) {

		List<Payment> payments = paymentService.findByPaymentAmountLessThan(paymentAmount);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payments);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/GreaterThan/{PaymentAmount}")
	@ApiOperation(value = "Show Payment greater than Payment Amount", response = Payment.class)
	public ResponseEntity<?> findByPaymentAmountGreater(@PathVariable("PaymentAmount") double paymentAmount) {

		List<Payment> payments = paymentService.findByPaymentAmountGreaterThan(paymentAmount);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(payments);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	

}
