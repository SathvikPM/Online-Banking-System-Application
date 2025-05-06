package com.tap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tap.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
	List<Account> findByUserId(int userId);
	
	Account findByAccountId(int accountId);
	
	
	@Modifying
	@Query(value = """
	    UPDATE accounts 
	    SET balance = balance + 
	        CASE 
	            WHEN :transactionType = 'deposit' THEN :amount 
	            WHEN :transactionType = 'withdraw' THEN -:amount 
	            ELSE 0 
	        END 
	    WHERE account_id = :accountId
	    """, nativeQuery = true)
	void updateBalanceByTransactionType(@Param("accountId") int accountId, 
	                                    @Param("amount") double amount,
	                                    @Param("transactionType") String transactionType);



}
