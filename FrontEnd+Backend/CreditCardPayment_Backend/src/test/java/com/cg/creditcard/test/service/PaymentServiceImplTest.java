package com.cg.creditcard.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.CreditCardType;
import com.cg.creditcard.enums.UserRole;
import com.cg.creditcard.exceptions.InvalidPaymentException;
import com.cg.creditcard.exceptions.PaymentAlreadyExistsException;
import com.cg.creditcard.exceptions.PaymentNotFoundException;
import com.cg.creditcard.repository.IPaymentRepository;
import com.cg.creditcard.service.PaymentServiceImpl;


@SpringBootTest
public class PaymentServiceImplTest {

	@InjectMocks 
	private PaymentServiceImpl paymentService;

	@Mock
	IPaymentRepository paymentRepository;
	
		@Test
		public void saveNewPaymentTest() throws PaymentNotFoundException 
		{
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(1);
			
			Mockito.when(paymentRepository.findById((long) 1)).thenReturn(Optional.of(payment));
			Mockito.when(paymentRepository.saveAndFlush(payment)).thenReturn(payment);

			Mockito.when(paymentRepository.findAll()).thenReturn(Stream.of(payment).collect(Collectors.toList()));
			List<Payment> actual = paymentService.getAllPayment();
			assertEquals(1,actual.size());
		}
		
		
		
		@Test
		public void savePaymentNullTest() throws PaymentAlreadyExistsException
		{
			Payment payment = null;
			Long cardId=null;
			Payment  actual = paymentService.addPayment(cardId,payment);
			assertNull(actual);
		}
		
		
		@Test
		public void savePaymentTest() {
			
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(1);
			CreditCard creditcard = new CreditCard();
			creditcard.setCardId(132l);
			creditcard.setBankName("HDFC");
			creditcard.setCardExpiry(LocalDate.of(2020, 10, 10));
			creditcard.setCardName("TEST");
			creditcard.setCardNumber(1236548787654321l);
			creditcard.setCardType(CreditCardType.MASTERCARD);
			creditcard.setCvv(321);

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

			creditcard.setUser(user);
			payment.setCreditcard(creditcard);

			Mockito.when(paymentRepository.save(payment)).thenThrow(new InvalidPaymentException("No Payment present in the database"));
			Exception exception = assertThrows(InvalidPaymentException.class,()->paymentService.addPayment(132l,payment));
			assertTrue(exception.getMessage().contains("No Payment present in the database"));
		}
		
		@Test
		public void getAllPaymentTest()
		{
			
			Payment payment = new Payment();
			
			Mockito.when(paymentRepository.findAll()).
					thenReturn(Stream.of(payment).collect(Collectors.toList()));
			List<Payment> actual = paymentService.getAllPayment();
			assertEquals(1,actual.size());
		}
		
		
		@Test
		public void noGetAllPaymentTest()
		{
			Mockito.when(paymentRepository.findAll()).thenThrow(new PaymentNotFoundException("No Payment present in the database"));

			Exception exception = assertThrows(PaymentNotFoundException.class,()->paymentService.getAllPayment());
			assertTrue(exception.getMessage().contains("No Payment present in the database"));
		}
		
		@Test 
		public void findPaymentById() throws PaymentNotFoundException 
		{
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(1);
			
			Mockito.when(paymentRepository.findById(payment.getPaymentId())).thenReturn(Optional.of(payment));
			Payment actual = paymentService.getPaymentById((long) 1);
			assertEquals(payment, actual);
		}
		
		
		@Test 
		public void findPaymentByIdNotPresentTest() {

			Mockito.when(paymentRepository.findById((long) 2000000)).thenThrow(new PaymentNotFoundException("Payment not present in the database"));
			Exception exception = assertThrows(PaymentNotFoundException.class,()->paymentService.getPaymentById((long) 2000000));
			assertTrue(exception.getMessage().contains("Payment not present in the database"));
		}
		
		
		@Test
		public void findPaymentByIdNullTest() throws PaymentNotFoundException
		{
			Long paymentId = null;
			Payment actual = paymentService.getPaymentById(paymentId);
			assertNull(actual);
		}

		@Test
		public void updatePaymentNullTest() throws PaymentNotFoundException
		{
			Payment payment = null;
			Payment actual = paymentService.updatePayment(null, payment);
			assertNull(actual);
		}

		@Test
		public void updatePaymentTestNotPresent()
		{
			
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(21);


			Mockito.when(paymentRepository.save(payment)).thenThrow(new PaymentNotFoundException("Payment Id not present in the database"));
			Exception exception = assertThrows(PaymentNotFoundException.class,()->paymentService.getPaymentById((long) 21));
			assertEquals("Payment not found for Payment Id: "+payment.getPaymentId(),exception.getMessage());
		}
		
		@Test
		public void updatePaymentNotPresentTest() throws PaymentNotFoundException 
		{
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(1);
			
			Mockito.when(paymentRepository.findById((long) 1)).thenThrow(new PaymentNotFoundException("Payment not present in the database"));
			Exception exception = assertThrows(PaymentNotFoundException.class,()->paymentService.getPaymentById(((long) 1)));
			assertTrue(exception.getMessage().contains("Payment not present in the database"));
		}

		@Test
		public void deletePaymentNullTest() throws PaymentNotFoundException
		{
			Long paymentId = null;
			Payment actual = paymentService.removePayment(paymentId);
			assertNull(actual);
		}

		@Test
		public void deletePaymentPresentTest() throws PaymentNotFoundException 
		{
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(1);

			

			given(paymentRepository.findById(payment.getPaymentId())).willReturn(Optional.of(payment));
			Payment expectedProduct = paymentService.removePayment((long) 1);
			Assertions.assertThat(expectedProduct).isNotNull();
			verify(paymentRepository,times(1)).deleteById(payment.getPaymentId());
			
			
		}

		
		@Test
		public void deletePaymentNotPresentTest() throws PaymentNotFoundException 
		{
			Payment payment = new Payment();
			
			payment.setStatus("Complted");
			payment.setPaymentId(21);

			

			Mockito.when(paymentRepository.findAll()).thenThrow(new PaymentNotFoundException("Payment not found for Payment:"));
			Exception exception = assertThrows(PaymentNotFoundException.class,()->paymentService.getPaymentById((long)21));
			assertEquals("Payment not found for Payment Id: "+payment.getPaymentId(),exception.getMessage());
			
			
		}
		 


		
		
		

		
}
