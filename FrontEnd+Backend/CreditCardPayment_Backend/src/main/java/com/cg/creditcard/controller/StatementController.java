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

import com.cg.creditcard.bean.Statement;
import com.cg.creditcard.payloads.BaseResponse;
import com.cg.creditcard.service.IStatementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage Statement module by providing the url to the
 * swagger We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */
@CrossOrigin
@RestController
@RequestMapping("/Statement")
@Api(value = "Statement Module", description = "Operations on Statement")
public class StatementController {

	@Autowired
	IStatementService statementService;

	@PostMapping("/add/{userId}")
	@ApiOperation(value = "Add a statement")
	public ResponseEntity<?> insertUser(@PathVariable("userId") Long userId, @Valid @RequestBody Statement statement) {
		statement = statementService.addStatement(userId, statement);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(statement);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Remove a statement")
	public ResponseEntity<?> deleteCustomerByCustId(@PathVariable("id") long id) {
		Statement statement = statementService.removeStatement(id);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(statement);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@PutMapping("/update")
	@ApiOperation(value = "Update a statement")
	public ResponseEntity<?> updateUser(@Valid @RequestBody Statement statement) {
		Long statementId = statement.getStatementId();
		statement = statementService.updateStatement(statementId, statement);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(statement);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}

	@GetMapping("/get/id/{statementId}")
	@ApiOperation(value = "Search a user with an ID", response = Statement.class)
	public ResponseEntity<?> findUserById(@PathVariable("statementId") Long id) {
		Statement statement = statementService.getStatement(id);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(statement);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	@GetMapping("/getall")
	@ApiOperation(value = "Show All Users")
	public ResponseEntity<?> getAllUsers() {
		List<Statement> statements = statementService.getAllStatements();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(statements);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

}