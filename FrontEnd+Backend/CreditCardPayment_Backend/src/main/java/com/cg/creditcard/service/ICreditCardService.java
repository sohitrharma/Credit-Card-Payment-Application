package com.cg.creditcard.service;

import java.util.List;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.enums.CreditCardType;

public interface ICreditCardService {

	public CreditCard addCreditCard(Long userId,CreditCard creditCard);
	public CreditCard removeCreditCard(Long cardId);
	public CreditCard updateCreditCard(Long userId, CreditCard card);
	public CreditCard getCreditCardById(Long cardId);
	public List<CreditCard> getAllCreditCards(); 
	public List<CreditCard>	getAllCreditCardsByName(String cardName);
	public CreditCard getCreditCardByNumber(Long cardNumber);
	public List<CreditCard> getAllCreditCardsByType(CreditCardType cardType);
	public List<CreditCard> getAllExpiredCreditCards();
	public List<CreditCard> getAllAboutToExpireCreditCards();
	public List<CreditCard> getAllNewCreditCards();
	public List<CreditCard> findAllCreditCardByUser(Long userId);
	public List<Payment> findAllPaymentByCardId(Long cardId);
}
