package com.cg.creditcard.test.service;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.assertj.core.api.Assertions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.creditcard.bean.Transaction;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.UserRole;
import com.cg.creditcard.exceptions.InvalidTransactionException;
import com.cg.creditcard.exceptions.TransactionNotFoundException;
import com.cg.creditcard.exceptions.TrnsactionAlreadyExistsException;
import com.cg.creditcard.repository.ITransactionRepostitory;
import com.cg.creditcard.repository.IUserRepository;
import com.cg.creditcard.service.TransactionServiceImpl;

@SpringBootTest
public class TransactionTest 
{
	
	
	
	@InjectMocks 
	private TransactionServiceImpl transactionService;

	@Mock
	ITransactionRepostitory transactionRepository;
	@Mock
	private IUserRepository userRepository;

	
		@Test
		public void saveTransactionTest() throws TransactionNotFoundException 
		{
			Transaction transaction = new Transaction();
			transaction.setCardNo("123456789025836");
			transaction.setDate(LocalDate.of(2002,12,20));
			transaction.setPayFrom("Pay no 9");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Done");
			transaction.setTime(LocalTime.of(12, 12, 12));
			transaction.setTranId((long) 123); 
			
			
			
			Mockito.when(transactionRepository.findById((long) 123)).thenReturn(Optional.of(transaction));
			Mockito.when(transactionRepository.saveAndFlush(transaction)).thenReturn(transaction);

			Mockito.when(transactionRepository.findAll()).thenReturn(Stream.of(transaction).collect(Collectors.toList()));
			List<Transaction> actual = transactionService.getAllTransaction();
			assertEquals(1,actual.size());
		}
		@Test
		public void saveTransactionNullTest() throws TrnsactionAlreadyExistsException
		{
			Transaction transaction = null;
			Transaction  actual = transactionService.addTransaction(null,transaction);
			assertNull(actual);
		}
		
		
		@Test
		public void saveExistingTransactionTest() {
			
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("1231231231231230");
			transaction.setDate(LocalDate.of(2020,12,12));
			transaction.setPayFrom("string");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Pending");
			transaction.setTime(LocalTime.of(12, 12, 12));
			transaction.setTranId((long) 63);
			User user = new User();
			user.setContactNo(3216549870l);
			user.setDob(LocalDate.of(2020, 10, 10));
			user.setEmail("test@email.com");
			user.setName("TEST");
			user.setPassword("TEst@123");
			user.setRole(UserRole.CUSTOMER);
			user.setUserId(6l);
			user.setAddress(null);
			user.setAccounts(null);
			user.setCreditcards(null);
			user.setStatements(null);
			transaction.setUser(user);

			Mockito.when(transactionRepository.save(transaction)).thenThrow(new InvalidTransactionException("No Transaction present in the database"));
			Exception exception = assertThrows(InvalidTransactionException.class,()->transactionService.addTransaction(user.getUserId(),transaction));
			assertTrue(exception.getMessage().contains("No Transaction present in the database"));
		}
		
		@Test
		public void getAllTransactionTest()
		{
			
			Transaction transaction = new Transaction();
			transaction.setCardNo("123456789025836");
			transaction.setDate(LocalDate.of(2002,12,20));
			transaction.setPayFrom("Pay no 9");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Done");
			transaction.setTime(LocalTime.of(12, 12, 12));
			transaction.setTranId((long) 13);
			
			Mockito.when(transactionRepository.findAll()).
					thenReturn(Stream.of(transaction).collect(Collectors.toList()));
			List<Transaction> actual = transactionService.getAllTransaction();
			assertEquals(1,actual.size());
		}
		
		@Test
		public void noGetAllTransactionTest()
		{
			Mockito.when(transactionRepository.findAll()).thenThrow(new TransactionNotFoundException("No Transaction present in the database"));

			Exception exception = assertThrows(TransactionNotFoundException.class,()->transactionService.getAllTransaction());
			assertTrue(exception.getMessage().contains("No Transaction present in the database"));
		}
		
		@Test 
		public void findTransactionById() throws TransactionNotFoundException {
			
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("1231231231231230");
			transaction.setDate(LocalDate.of(2020,12,12));
			transaction.setPayFrom("string");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Pending");
			transaction.setTime(LocalTime.of(12, 12, 12));
			
			transaction.setTranId((long) 62);
			
			Mockito.when(transactionRepository.findById(transaction.getTranId())).thenReturn(Optional.of(transaction));
			Transaction actual = transactionService.getTransactionById((long) 62);
			assertEquals(transaction, actual);
		}
		
		@Test 
		public void findTransactionByIdNotPresentTest() {

			Mockito.when(transactionRepository.findById((long) 2000000)).thenThrow(new TransactionNotFoundException("Trasaction not present in the database"));
			Exception exception = assertThrows(TransactionNotFoundException.class,()->transactionService.getTransactionById((long) 2000000));
			assertTrue(exception.getMessage().contains("Trasaction not present in the database"));
		}
		
		
		@Test
		public void findTransactionByIdNullTest() throws TransactionNotFoundException
		{
			Long tranId = null;
			Transaction actual = transactionService.getTransactionById(tranId);
			assertNull(actual);
		}

		@Test
		public void updateTransactionNullTest() throws TransactionNotFoundException
		{
			Transaction transaction = null;
			Transaction actual = transactionService.updateTransaction(null, transaction);
			assertNull(actual);
		}

		@Test
		public void updateTransactionTestPresent()
		{
			
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("123456789025836");
			transaction.setDate(LocalDate.of(2002,12,20));
			transaction.setPayFrom("Pay no 9");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Done");
			transaction.setTime(LocalTime.of(12, 12, 12));
			
			transaction.setTranId((long) 1243258441);

			Mockito.when(transactionRepository.save(transaction)).thenThrow(new TransactionNotFoundException("Transaction Id not present in the database"));
			Exception exception = assertThrows(TransactionNotFoundException.class,()->transactionService.getTransactionById((long) 1243258441));
			assertEquals("Transaction not found for transaction Id:"+transaction.getTranId(),exception.getMessage());
		}
		
		@Test
		public void updateTransactionNotPresentTest() throws TransactionNotFoundException 
		{
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("123456789025836");
			transaction.setDate(LocalDate.of(2002,12,20));
			transaction.setPayFrom("Pay no 9");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Done");
			transaction.setTime(LocalTime.of(12, 12, 12));
			
			transaction.setTranId((long) 1);
			
			Mockito.when(transactionRepository.findById((long) 1)).thenThrow(new TransactionNotFoundException("Transaction not present in the database"));
			Exception exception = assertThrows(TransactionNotFoundException.class,()->transactionService.getTransactionById((long) 1));
			assertTrue(exception.getMessage().contains("Transaction not present in the database"));
		}

		@Test
		public void deleteTransactionNullTest() throws TransactionNotFoundException
		{
			Long tranId = null;
			Transaction actual = transactionService.removeTransaction(tranId);
			assertNull(actual);
		}

		@Test
		public void deleteTransactionPresentTest() throws TransactionNotFoundException 
		{
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("1231231231231230");
			transaction.setDate(LocalDate.of(2020,12,12));
			transaction.setPayFrom("string");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Pending");
			transaction.setTime(LocalTime.of(12, 12, 12));
			transaction.setTranId((long) 63);	
			

			given(transactionRepository.findById(transaction.getTranId())).willReturn(Optional.of(transaction));
			Transaction expectedProduct = transactionService.removeTransaction((long) 63);
			Assertions.assertThat(expectedProduct).isNotNull();
			verify(transactionRepository,times(1)).deleteById(transaction.getTranId());
			
			
		}

		
		@Test
		public void deleteTransactionNotPresentTest() throws TransactionNotFoundException 
		{
			Transaction transaction = new Transaction();
			
			transaction.setCardNo("123456789025836");
			transaction.setDate(LocalDate.of(2002,12,20));
			transaction.setPayFrom("Pay no 9");
			transaction.setPaymentAmount(2500);
			transaction.setStatus("Done");
			transaction.setTime(LocalTime.of(12, 12, 12));
			transaction.setTranId((long) 1);
			

			Mockito.when(transactionRepository.findAll()).thenThrow(new TransactionNotFoundException("Transaction not found for transaction:"));
			Exception exception = assertThrows(TransactionNotFoundException.class,()->transactionService.getTransactionById((long) 1));
			assertEquals("Transaction not found for transaction Id:"+transaction.getTranId(),exception.getMessage());
			
			
		}
}
