package com.cg.creditcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cg.creditcard.bean.Account;
import com.cg.creditcard.enums.AccountType;


public interface IAccountRepository extends JpaRepository<Account,Long> {
 

	    public List<Account> findAllAccountByAccountName(String accountName);

	 

	    public List<Account> findAllAccountByAccountType(AccountType accountType);

}
