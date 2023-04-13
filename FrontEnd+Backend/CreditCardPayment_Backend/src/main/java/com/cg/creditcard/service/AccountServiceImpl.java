package com.cg.creditcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.Account;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.enums.AccountType;
import com.cg.creditcard.exceptions.AccountException;
import com.cg.creditcard.exceptions.AccountNotFoundException;
import com.cg.creditcard.repository.*;

import com.cg.creditcard.util.AccountConstants;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	
	@Override
	public Account addAccount(Long userId,Account account) {
		if(account==null || userId==null)
			return null;
		User user=userRepository.findById(userId).get();
		Optional<Account> checking = accountRepository.findById((account).getAccountNumber());
		if(checking.isPresent())
			throw new AccountNotFoundException("Account already exists...");
		
		try {
			account.setUser(user);
			accountRepository.save(account);
		}catch(Exception e) {
			throw new AccountException("Account Not added!!!" + e.getMessage());			
		}
		return account;
	}
	@Override
	public Account removeAccount(Long accountNumber)  {
		if(accountNumber == null)
			return null;
		Optional<Account> checking = accountRepository.findById(accountNumber);
		if(!checking.isPresent()) {
			throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND_BY_ACCOUNTNUMBER + accountNumber);
		}
		Account account = accountRepository.findById(accountNumber).get();
		try {
			accountRepository.deleteById(accountNumber);
		}catch(Exception e) {
			throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_GET_BY_ACCOUNTNUMBER + e.getMessage());			
		}
		
		return account;
	}
	@Override
	public Account updateAccount(Long accountNumber,Account account)  {
		if(accountNumber==null) {
			return null;
		}
		else {
			Optional<Account> checking = accountRepository.findById(accountNumber);
			if(!checking.isPresent()) {
				throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND_BY_ACCOUNTNUMBER + accountNumber);
				
			}else {
				Account tempAccount=accountRepository.findById(accountNumber).get();
				account=(tempAccount);
				try {
					accountRepository.save(account);
				}catch(Exception e) {
					throw new AccountException(AccountConstants.ACCOUNT_NOT_GET_BY_ACCOUNTNUMBER + e.getMessage());			
					
				}
			}
		}
		return account;
	}
	@Override
	public Account getAccountByNumber(Long accountNumber)  {
		if(accountNumber == null) {
			return null;
		}
		Optional<Account> checking = accountRepository.findById(accountNumber);
		if(!checking.isPresent()) {
			throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND_BY_ACCOUNTNUMBER + accountNumber);
		}
		Account account;
		try {
			account= accountRepository.findById(accountNumber).get();
		}catch(Exception e) {
			throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_GET_BY_ACCOUNTNUMBER + e.getMessage());						
		}
		
		return account;
	}
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts=new ArrayList<>();
		for(Account a:accountRepository.findAll()) {
			accounts.add(a);
		}
		if(accounts.isEmpty()) {
			throw new AccountNotFoundException("No accounts present in the database");
		}
		return accounts;
		
	}
	@Override
	public List<Account> getAllAccountsByAccountName(String accountName) {
		 List<Account> accounts = new ArrayList<>();
	        accountRepository.findAllAccountByAccountName(accountName).forEach(accounts::add);
	       
	        if(accounts.size()<1)
	            throw new AccountNotFoundException("Account name not present..Please enter is correct Format");
	       
	        List<Account> accountsReturn = new ArrayList<>();
	        for (Account t : accounts ) {
	            accountsReturn.add(t);
	        }
	        return accountsReturn;
	 
	}
	@Override
	public List<Account> getAllAccountsByAccountType(AccountType accountType) {
		List<Account> accounts = new ArrayList<>();
        accountRepository.findAllAccountByAccountType(accountType).forEach(accounts::add);
       
        if(accounts.size()<1)
            throw new AccountNotFoundException("Account type is not present..Please enter is correct Format");
       
        List<Account> accountsReturn = new ArrayList<>();
        for (Account t : accounts ) {
            accountsReturn.add(t);
        }
        return accountsReturn;
	}
}
