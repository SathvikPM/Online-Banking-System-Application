package com.tap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tap.model.Account;
import com.tap.model.Transaction;
import com.tap.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository repo;

	public void saveTransaction(Transaction transaction) {
		repo.save(transaction);
	}

	public List<Transaction> getTransactionsByAccountId(int accountId) {
		System.out.println("in TransactionService");
		return repo.findByAccountAccountId(accountId);
	}
}
