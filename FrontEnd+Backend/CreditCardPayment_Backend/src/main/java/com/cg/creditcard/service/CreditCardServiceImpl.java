package com.cg.creditcard.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.CreditCardType;
import com.cg.creditcard.exceptions.CreditCardNotFoundException;
import com.cg.creditcard.exceptions.InvalidCreditCardException;
import com.cg.creditcard.repository.ICreditCardRepository;
import com.cg.creditcard.repository.IUserRepository;
import com.cg.creditcard.util.CreditCardConstants;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

	@Autowired
	private ICreditCardRepository creditcardRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public CreditCard addCreditCard(Long userId, CreditCard creditCard) {
		if (creditCard == null || userId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Please enter all the creditcard details with the user id.");
		User user = userRepository.findById(userId).get();

		Optional<CreditCard> checking = creditcardRepository.findById(creditCard.getCardId());
		if (checking.isPresent())
			throw new InvalidCreditCardException("CreditCard Already Exists");

		try {
			creditCard.setUser(user);
			creditcardRepository.save(creditCard);
		} catch (Exception e) {
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST + e.getMessage());
		}
		return creditCard;
	}

	@Override
	public CreditCard removeCreditCard(Long cardId) {
		if (cardId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Card ID cannot be null.Please enter creditcard Id.");
		Optional<CreditCard> checking = creditcardRepository.findById(cardId);
		if (!checking.isPresent()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_ID_CONST + cardId);
		}
		CreditCard creditCard = creditcardRepository.findById(cardId).get();
		try {
			creditcardRepository.deleteById(cardId);
		} catch (Exception e) {
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST + e.getMessage());
		}

		return creditCard;
	}

	@Override
	public CreditCard updateCreditCard(Long userId, CreditCard card) {
		User user = userRepository.findById(userId).get();
		Long cardId=card.getCardId();
		if (card == null || userId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Please enter all the creditcard details with the user id.");
		

		else {
			Optional<CreditCard> checking = creditcardRepository.findById(cardId);
			if (!checking.isPresent()) {
				throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_ID_CONST + cardId);
			} else {
				CreditCard creditCard = creditcardRepository.findById(cardId).get();
				creditCard = (card);
				try {
					creditCard.setUser(user);
					creditcardRepository.save(creditCard);
				} catch (Exception e) {
					throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST + e.getMessage());
				}
			}
		}
		return card;
	}

	@Override
	public CreditCard getCreditCardById(Long cardId) {
		if (cardId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Card ID cannot be null.Please enter creditcard Id.");
		Optional<CreditCard> checking = creditcardRepository.findById(cardId);
		if (!checking.isPresent()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_ID_CONST + cardId);
		}
		CreditCard creditCard;
		try {
			creditCard = creditcardRepository.findById(cardId).get();
		} catch (Exception e) {
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST + e.getMessage());
		}

		return creditCard;
	}

	@Override
	public List<CreditCard> getAllCreditCards() {
		List<CreditCard> creditCards = new ArrayList<>();
		for (CreditCard c : creditcardRepository.findAll()) {
			creditCards.add(c);
		}
		if (creditCards.isEmpty()) {
			throw new CreditCardNotFoundException("No CreditCards present in the database");
		}
		return creditCards;
	}

	@Override
	public List<CreditCard> getAllCreditCardsByName(String cardName) {
		List<CreditCard> creditCards = new ArrayList<>();
		for (CreditCard c : creditcardRepository.findAllCreditCardByCardName(cardName)) {
			creditCards.add(c);
		}
		if (creditCards.isEmpty()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_NAME_CONST + cardName);
		}
		return creditCards;
	}

	@Override
	public CreditCard getCreditCardByNumber(Long cardNumber) {
		CreditCard creditCard;
		if (cardNumber == null) {
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Card Number cannot be null.Please enter 16 digit card number.");
		}
		if (cardNumber.toString().length() != 16) {
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST + "Invalid Card Number");
		} else {
			CreditCard checking = (creditcardRepository.findCreditCardByCardNumber(cardNumber));
			if (checking == null) {
				throw new CreditCardNotFoundException(
						CreditCardConstants.CREDITCARD_NOT_FOUND_BY_NUMBER_CONST + cardNumber);
			}
			try {
				creditCard = creditcardRepository.findCreditCardByCardNumber(cardNumber);
			} catch (Exception e) {
				throw new InvalidCreditCardException(
						CreditCardConstants.CREDITCARD_NOT_FOUND_BY_NUMBER_CONST + cardNumber);
			}

		}
		return creditCard;
	}

	@Override
	public List<CreditCard> getAllCreditCardsByType(CreditCardType cardType) {
		List<CreditCard> creditCards = new ArrayList<>();
		for (CreditCard c : creditcardRepository.findAllCreditCardByCardType(cardType)) {
			creditCards.add(c);
		}
		if (creditCards.isEmpty()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_TYPE_CONST + cardType);
		}
		return creditCards;
	}

	@Override
	public List<CreditCard> getAllExpiredCreditCards() {
		List<CreditCard> creditCards = getAllCreditCards();
		List<CreditCard> expiredlist = new ArrayList<>();
		for (CreditCard c : creditCards) {
			if (c.getCardExpiry().compareTo(LocalDate.now()) <= 0) {
				expiredlist.add(c);
			}
		}

		return expiredlist;
	}

	@Override
	public List<CreditCard> getAllAboutToExpireCreditCards() {
		List<CreditCard> creditCards = getAllCreditCards();
		List<CreditCard> abouttoexpirelist = new ArrayList<>();
		for (CreditCard c : creditCards) {
			if (!(c.getCardExpiry().compareTo(LocalDate.now()) >= 15)
					&& !(c.getCardExpiry().compareTo(LocalDate.now()) <= 0)) {
				abouttoexpirelist.add(c);
			}
		}
		return abouttoexpirelist;
	}

	@Override
	public List<CreditCard> getAllNewCreditCards() {
		List<CreditCard> creditCards = getAllCreditCards();
		List<CreditCard> newcardlist = new ArrayList<>();
		for (CreditCard c : creditCards) {
			if (c.getCardExpiry().compareTo(LocalDate.now()) >= 15) {
				newcardlist.add(c);
			}
		}
		return newcardlist;
	}

	@Override
	public List<CreditCard> findAllCreditCardByUser(Long userId) {
		if ( userId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Please valid user id.");
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new CreditCardNotFoundException(CreditCardConstants.USER_NOT_FOUND_BY_USER_ID_CONST + userId);
		}
		List<CreditCard> creditCards = new ArrayList<>();
		for (CreditCard c : creditcardRepository.findAllCreditCardByUser(userId)) {
			creditCards.add(c);
		}
		if (creditCards.isEmpty()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_USER_ID_CONST + userId);
		}
		return creditCards;
	}

	@Override
	public List<Payment> findAllPaymentByCardId(Long cardId) {
		if (cardId == null)
			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
					+ "Card ID cannot be null.Please enter creditcard Id.");
		Optional<CreditCard> checking = creditcardRepository.findById(cardId);
		if (!checking.isPresent()) {
			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_ID_CONST + cardId);
		}
		CreditCard creditcard=creditcardRepository.findById(cardId).get();
		List<Payment> payments=new ArrayList<>();
		payments.addAll(creditcard.getPayments());
		if (payments.isEmpty()) {
			throw new CreditCardNotFoundException(CreditCardConstants.PAYMENT_NOT_FOUND_BY_CARD_ID_CONST + cardId);
		}
		
		return payments;
	}

}
