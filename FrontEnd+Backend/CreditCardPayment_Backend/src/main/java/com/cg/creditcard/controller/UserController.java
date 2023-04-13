package com.cg.creditcard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcard.bean.User;
import com.cg.creditcard.payloads.BaseResponse;
import com.cg.creditcard.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class allows to manage User module by providing the url to the swagger
 * We need to take some from the user in this class.
 * 
 * @RestController takes care of mapping request data to the defined request
 *                 handler method.
 * @RequestMapping annotation is when used to map Spring MVC controller methods.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Api(value = "User Module", description = "Operations on user")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/add")
	@ApiOperation(value = "Add a User")
	public ResponseEntity<?> insertUser(@Valid @RequestBody User user) {
		user = userService.addUser(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(user);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
	}

	/*
	 * @DeleteMapping("/delete/{userId}")
	 * 
	 * @ApiOperation(value = "Remove a user") public ResponseEntity<?>
	 * deleteCustomerByCustId(@PathVariable("userId") long userId) { User user =
	 * userService.removeUser(userId); BaseResponse baseResponse = new
	 * BaseResponse(); baseResponse.setStatusCode(1);
	 * baseResponse.setResponse(user); return new ResponseEntity<>(baseResponse,
	 * HttpStatus.OK); }
	 */

	@PutMapping("/update")
	@ApiOperation(value = "Update a user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
		Long userId = user.getUserId();
		user = userService.updateUser(userId, user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(user);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}

	@GetMapping("/get/id/{id}")
	@ApiOperation(value = "Search a user with an ID", response = User.class)
	public ResponseEntity<?> findUser(@PathVariable("id") Long id) {
		User user = userService.getUser(id);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(user);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/getall")
	 * 
	 * @ApiOperation(value = "Show All Users") public ResponseEntity<?>
	 * getAllUsers() { List<User> users = userService.getAllUsers(); BaseResponse
	 * baseResponse = new BaseResponse(); baseResponse.setStatusCode(1);
	 * baseResponse.setResponse(users); return new ResponseEntity<>(baseResponse,
	 * HttpStatus.OK); }
	 */

	@PostMapping("/login")
	@ApiOperation(value = "login a user")
	public ResponseEntity<?> signIn(@RequestBody User user) {
		user = userService.signIn(user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(user);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);

	}

	@CrossOrigin(origins = "*")
	@PutMapping("/resetPassword")
	@ApiOperation(value = "forget Password")
	public ResponseEntity<?> forgetPassword(@Valid @RequestBody User user) {
		Long userId = user.getUserId();
		user = userService.changePassword(userId, user);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(1);
		baseResponse.setResponse(user);
		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);

	}
	
}