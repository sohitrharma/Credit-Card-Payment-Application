package com.cg.creditcard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcard.bean.Account;
import com.cg.creditcard.enums.AccountType;
import com.cg.creditcard.payloads.BaseResponse;
import com.cg.creditcard.service.IAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage Account module by providing the url to the
 * swagger We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */

@CrossOrigin
@RestController
@RequestMapping("/Account")
@Api(value="Account Module" , description="Operations on Account")
public class AccountController {
	@Autowired
	IAccountService accountService;
	
	@PostMapping("/add/{userId}")
	@ApiOperation(value = "Add an account")
	public ResponseEntity<?> addAccount(@PathVariable("userId") long userId,@Valid @RequestBody Account account)
	{
		account= accountService.addAccount(userId,account);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(account);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{accountNumber}")
	@ApiOperation(value = "Remove an account")
	public ResponseEntity<?> deleteAccountByAccountNumber(@PathVariable("accountNumber") Long accountNumber)
	{
		Account account=accountService.removeAccount(accountNumber);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(account);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	@PutMapping("/Update")
	@ApiOperation(value="Update an account")
	public ResponseEntity<?> updateAccount (@Valid @RequestBody Account account) 
	{
		long accountNumber=account.getAccountNumber();
		account=accountService.updateAccount(accountNumber,account);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(account);
		
		return new ResponseEntity<>(baseResponse , HttpStatus.CREATED);
			
	}
	@GetMapping("/get/accountNumber/{accountNumber}")
	@ApiOperation(value = "Search an account By AccountNumber",response = Account.class)
	public ResponseEntity<?> findByAccountNumber(@PathVariable("accountNumber") Long accountNumber) 
	{
		Account account = accountService.getAccountByNumber(accountNumber);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(account);
		
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	@GetMapping("/getall")
	@ApiOperation(value = "show All accounts")
	public ResponseEntity<?> getAllAccounts(){
		List<Account> accountList = accountService.getAllAccounts();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(accountList);
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}
	
	@GetMapping("/getallbyname/{accountName}")
	@ApiOperation(value = "show All accounts")
	public ResponseEntity<?> getAllAccountsByName(@PathVariable("accountNumber")String accountName){
		List<Account> accountList = accountService.getAllAccountsByAccountName(accountName);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(accountList);
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}
	@GetMapping("/getallbytype/{accountType}")
	@ApiOperation(value = "show All accounts")
	public ResponseEntity<?> getAllAccountsByType(@PathVariable("accountType")AccountType accountType){
		List<Account> accountList = accountService.getAllAccountsByAccountType(accountType);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(accountList);
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}

}

