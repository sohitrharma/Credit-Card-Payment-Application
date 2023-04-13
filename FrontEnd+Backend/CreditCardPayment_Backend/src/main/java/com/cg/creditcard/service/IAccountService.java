package com.cg.creditcard.service;

import java.util.List;


import com.cg.creditcard.bean.Account;
import com.cg.creditcard.enums.AccountType;


public interface IAccountService {
	public Account addAccount(Long userId,Account account);
	public Account removeAccount(Long accountNumber) ;
	public Account updateAccount(Long accountNumber,Account account);
	public Account getAccountByNumber(Long accountNumber);
	public List<Account> getAllAccounts();
	public List<Account> getAllAccountsByAccountName(String accountName);
	public List<Account> getAllAccountsByAccountType(AccountType accountType);
}