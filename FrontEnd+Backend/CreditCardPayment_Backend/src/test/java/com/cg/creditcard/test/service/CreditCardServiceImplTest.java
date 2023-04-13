package com.cg.creditcard.test.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.CreditCardType;
import com.cg.creditcard.enums.UserRole;
import com.cg.creditcard.exceptions.CreditCardNotFoundException;
import com.cg.creditcard.exceptions.InvalidCreditCardException;
import com.cg.creditcard.repository.ICreditCardRepository;
import com.cg.creditcard.repository.IUserRepository;
import com.cg.creditcard.service.CreditCardServiceImpl;
import com.cg.creditcard.service.UserServiceImpl;

@SpringBootTest
public class CreditCardServiceImplTest {

	@Mock
	private ICreditCardRepository creditcardRepository;

	@Mock
	private IUserRepository userRepository;

	@InjectMocks
	private CreditCardServiceImpl creditcardService;

	@InjectMocks
	private UserServiceImpl userService;

	@DisplayName("Sample test")
	@Test
	void sampleTest() {
		assertTrue(true);
	}

	@Test
	public void saveCreditCardTest() throws InvalidCreditCardException {
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

		when(userRepository.findById(6l)).thenReturn(Optional.of(user));
		when(creditcardRepository.findById(132l)).thenReturn(Optional.of(creditcard));
		when(creditcardRepository.save(creditcard)).thenReturn(creditcard);
		when(creditcardRepository.findAll()).thenReturn(Stream.of(creditcard).collect(Collectors.toList()));
		List<CreditCard> actual = creditcardService.getAllCreditCards();
		assertEquals(1, actual.size());

	}

	@Test
	public void getAllCreditCardsTest() {
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

		when(creditcardRepository.findAll()).thenReturn(Stream.of(creditcard).collect(Collectors.toList()));
		List<CreditCard> actual = creditcardService.getAllCreditCards();
		assertEquals(1, actual.size());
	}

	@Test
	public void findCreditCardById() throws CreditCardNotFoundException {
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

		when(creditcardRepository.findById(132l)).thenReturn(Optional.of(creditcard));
		CreditCard actual = creditcardService.getCreditCardById(132l);
		assertEquals(creditcard, actual);
	}

	@Test
	public void updateCreditCardTestPresent() {
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

		given(userRepository.findById(6l)).willReturn(Optional.of(user));
		given(creditcardRepository.findById(132l)).willReturn(Optional.of(creditcard));
		given(creditcardRepository.save(creditcard)).willReturn(creditcard);
		CreditCard expectedProduct = creditcardService.updateCreditCard(creditcard.getCardId(), creditcard);
		assertThat(expectedProduct).isNotNull();
		verify(creditcardRepository).save(any(CreditCard.class));
	}

	@Test
	public void findCreditCardByIdNotPresentTest() {
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

		final long cardId = 1000l;
		Exception exception = assertThrows(CreditCardNotFoundException.class, () -> {
			creditcardService.getCreditCardById(cardId);
		});
		String expectedMessage = "CreditCard not found for card Id: " + cardId;
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage, expectedMessage);
	}

	@Test
	public void testFindAllProducts() {
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

		List<CreditCard> expectedCreditcards = Arrays.asList(creditcard);
		given(creditcardRepository.findAll()).willReturn(expectedCreditcards);
		List<CreditCard> actualProducts = creditcardService.getAllCreditCards();
		assertThat(actualProducts).isEqualTo(expectedCreditcards);
	}

	@Test
	public void testRemoveCreditCard() {
		final long cardId = 132l;
		CreditCard creditcard = new CreditCard();
		creditcard.setCardId(cardId);

		given(creditcardRepository.findById(cardId)).willReturn(Optional.of(creditcard));
		creditcardService.removeCreditCard(cardId);
		creditcardService.removeCreditCard(cardId);
		verify(creditcardRepository, times(2)).deleteById(cardId);

	}
}
