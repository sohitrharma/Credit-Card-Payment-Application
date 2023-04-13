package com.cg.creditcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.Payment;
import com.cg.creditcard.enums.CreditCardType;

public interface ICreditCardRepository extends JpaRepository<CreditCard, Long> {

	public CreditCard findCreditCardByCardNumber(Long cardNumber);

	public List<CreditCard> findAllCreditCardByCardName(String cardName);

	public List<CreditCard> findAllCreditCardByCardType(CreditCardType cardType);

	public List<CreditCard> findAllCreditCardByUser(Long userId);
	
	public List<Payment> findAllPaymentByCardId(Long cardId);
}
