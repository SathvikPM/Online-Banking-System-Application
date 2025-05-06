package com.tap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tap.model.Account;
import com.tap.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // You can define custom query methods here if needed
	
	List<Transaction> findByAccountAccountId(int accountId);

}
