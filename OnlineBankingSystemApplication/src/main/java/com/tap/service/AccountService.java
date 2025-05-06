package com.tap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tap.model.Account;
import com.tap.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository repo;
	
	
	
	public void saveAccount(Account account) {
		repo.save(account);
		
	}
	
	public List<Account> getAccountsById(int userId) {
		return repo.findByUserId(userId);
	}
	
	public Account getAccountById(int accountId) {
		return repo.findByAccountId(accountId);
	}

	

	@Transactional
	public void updateBalance(int accountId, double amount, String transactionType) {
		repo.updateBalanceByTransactionType(accountId,amount,transactionType);
		
	}
}
