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

import com.cg.creditcard.service.ICreditCardService;
import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.enums.CreditCardType;
import com.cg.creditcard.payloads.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage CreditCard module by providing the url to the
 * swagger We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/creditcard")
@Api(value = "CreditCard Module", description = "Operations on CreditCard")
public class CreditCardController {

	@Autowired
	ICreditCardService creditCardService;

	@PostMapping("/add/{userId}")
	@ApiOperation(value = "Add a CreditCard")
	public ResponseEntity<?> saveCreditCard(@PathVariable("userId") long userId,
			@Valid @RequestBody CreditCard creditCard) {
		creditCard = creditCardService.addCreditCard(userId, creditCard);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCard);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{cardId}")
	@ApiOperation(value = "Remove a CreditCard")
	public ResponseEntity<?> deleteCreditCardByCardId(@PathVariable("cardId") long cardId) {
		CreditCard creditCard = creditCardService.removeCreditCard(cardId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCard);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

//	
	@PutMapping("/update/{userId}")
	@ApiOperation(value = "Update a CreditCard")
	public ResponseEntity<?> updateCreditCard(@PathVariable("userId") Long userId,@Valid @RequestBody CreditCard creditCard) {
		creditCard = creditCardService.updateCreditCard(userId, creditCard);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCard);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}

//	
	@GetMapping("/get/id/{cardId}")
	@ApiOperation(value = "Search a CreditCard with an ID", response = CreditCard.class)
	public ResponseEntity<?> findCreditCardById(@PathVariable("cardId") long cardId) {
		CreditCard creditCardDTO = creditCardService.getCreditCardById(cardId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCardDTO);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall")
	@ApiOperation(value = "Show all CreditCards")
	public ResponseEntity<?> getAllCreditCards() {
		List<CreditCard> creditCards = creditCardService.getAllCreditCards();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall/name/{cardName}")
	@ApiOperation(value = "Show all CreditCards by Card Name")
	public ResponseEntity<?> getAllCreditCardsByName(@PathVariable("cardName") String cardName) {
		List<CreditCard> creditCards = creditCardService.getAllCreditCardsByName(cardName);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/get/num/{cardNumber}")
	@ApiOperation(value = "Search a CreditCard by Card Number", response = CreditCard.class)
	public ResponseEntity<?> findCreditCardByNumber(@PathVariable("cardNumber") Long cardNumber) {
		CreditCard creditCardDTO = creditCardService.getCreditCardByNumber(cardNumber);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCardDTO);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall/type/{cardType}")
	@ApiOperation(value = "Show all CreditCards by Card Type")
	public ResponseEntity<?> getAllCreditCardsByType(@PathVariable("cardType") CreditCardType cardType) {
		List<CreditCard> creditCards = creditCardService.getAllCreditCardsByType(cardType);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall/expired")
	@ApiOperation(value = "Show all Expired CreditCards")
	public ResponseEntity<?> getAllExpiredCreditCards() {
		List<CreditCard> creditCards = creditCardService.getAllExpiredCreditCards();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall/expiresoon")
	@ApiOperation(value = "Show all CreditCards which will expire soon")
	public ResponseEntity<?> getAllCreditCardsAboutToExpire() {
		List<CreditCard> creditCards = creditCardService.getAllAboutToExpireCreditCards();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall/new")
	@ApiOperation(value = "Show all new CreditCards")
	public ResponseEntity<?> getAllNewCreditCards() {
		List<CreditCard> creditCards = creditCardService.getAllNewCreditCards();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getall/userId/{userId}")
	@ApiOperation(value = "Show all CreditCards by userId")
	public ResponseEntity<?> getAllCreditCardsByUserId(@PathVariable("userId") Long userId) {
		List<CreditCard> creditCards = creditCardService.findAllCreditCardByUser(userId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	
	@GetMapping("/getallpayments/{cardId}")
	@ApiOperation(value = "Show all Payments by cardId")
	public ResponseEntity<?> getAllPaymentsByCardId(@PathVariable("cardId") Long cardId) {
		List<Payment> creditCards = creditCardService.findAllPaymentByCardId(cardId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(creditCards);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	
}
