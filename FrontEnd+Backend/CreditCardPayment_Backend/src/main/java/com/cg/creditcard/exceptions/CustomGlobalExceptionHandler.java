package com.cg.creditcard.exceptions;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.creditcard.payloads.BaseErrorResponse;
import com.cg.creditcard.payloads.ValidationErrorResponse;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final String BAD_REQUEST = "BAD_REQUEST";

	@ExceptionHandler(StatementNotFoundException.class)
	public ResponseEntity<?> customHandleStatementNotFound(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(404);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<?> customHandleTransactionNotFound(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(-1);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidStatementException.class)
	public ResponseEntity<?> customHandleStatementInvalid(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(404);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidTransactionException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(InvalidTransactionException ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(CreditCardNotFoundException.class)
	public ResponseEntity<?> customHandleNotFound(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(404);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> customHandleAccountNotFound(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(-1);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(UserAlreadyExistsException ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UsersNotFoundException.class)
	public ResponseEntity<?> customHandlesNotFound(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(-1);

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
			WebRequest request) {
		List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
				.collect(Collectors.toList());

		ValidationErrorResponse validationErrResp = new ValidationErrorResponse();
		validationErrResp.setTimestamp(LocalDateTime.now());
		validationErrResp.setMessage(BAD_REQUEST);
		validationErrResp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		validationErrResp.setDetails(details);

		return new ResponseEntity<>(validationErrResp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCreditCardException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(InvalidCreditCardException ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public final ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//
//		//ErrorResponse error = new ErrorResponse(BAD_REQUEST, errors);
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

//Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> customHandleForServerError(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
