package com.cg.creditcard.bean;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.cg.creditcard.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * 
 * @author Jahnavi
 * 
 * */

@Entity //This annotation indicates this class is a entity class
@Table(name="Account_details") //This annotation is used to create and declare the table name
public class Account {

	@Id
	@Column(name="accountNumber")
	@Min(value = 100000000000l,message = "Please enter valid card number")
    @Max(value = 999999999999l,message = "Please enter valid card number")
	private Long accountNumber;
	
	@Column(name="accountName")
	@NotEmpty(message = "Account name cannot be empty.Please enter the accountname")
	private String accountName;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="accountType")
	private AccountType accountType;
	
	@ManyToOne
	@JoinColumn(name="userId",nullable=true)
//	@JsonIgnore
	private User user;
		
	public Account() {
		super();
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account(
			@Min(value = 100000000000l, message = "Please enter valid card number") @Max(value = 999999999999l, message = "Please enter valid card number") Long accountNumber,
			@NotEmpty(message = "Account name cannot be empty.Please enter the accountname") String accountName,
			double balance, AccountType accountType, User user) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.accountType = accountType;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountName=" + accountName + ", balance=" + balance
				+ ", accountType=" + accountType + ", user=" + user + "]";
	}

	

	
	
	
	
	
}
