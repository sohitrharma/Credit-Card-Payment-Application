package com.cg.creditcard.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cg.creditcard.enums.CreditCardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "creditcard_tbl")
public class CreditCard {
	@Id
	@GeneratedValue
	@Column(name = "card_Id")
	private long cardId;

	@Column(name = "bank_Name")
	@NotEmpty(message = "Bank name cannot be empty.Please enter the bank name")
	private String bankName;

	@Column(name = "card_Type")
	@Enumerated(EnumType.STRING)
	private CreditCardType cardType;

	@Column(name = "card_Name")
	@NotEmpty(message = "Creditcard name cannot be empty.Please enter the creditcard name")
	private String cardName;

	@Column(name = "card_Number", unique = true)
	@Min(value = 4000000000000000l, message = "Please enter valid card number")
	@Max(value = 5999999999999999l, message = "Please enter valid card number")
	@NotNull
	private Long cardNumber;

	@Column(name = "card_Expiry")
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cardExpiry;

	@Column(name = "cvv", unique = true)
	@Min(value = 100, message = "Please enter valid CVV")
	@Max(value = 999, message = "Please enter valid CVV")
	private int cvv;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = true)
//	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "creditcard", cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Payment> payments = new ArrayList<>();

	public CreditCard() {
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public CreditCardType getCardType() {
		return cardType;
	}

	public void setCardType(CreditCardType cardType) {
		this.cardType = cardType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(LocalDate cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonManagedReference
	public List<Payment> getPayments() {
		return payments;
	}
 
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
