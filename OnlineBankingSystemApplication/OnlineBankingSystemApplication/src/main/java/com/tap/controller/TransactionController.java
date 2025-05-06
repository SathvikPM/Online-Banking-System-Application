package com.tap.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.model.Account;
import com.tap.model.Transaction;
import com.tap.model.User;
import com.tap.service.AccountService;
import com.tap.service.TransactionService;
import com.tap.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userservice;
	
	@GetMapping("/transaction")
	public String showTransaction() {
		return "transaction";
	}

	@PostMapping("/transaction")
	public String addTransaction(@RequestParam int accountId,
			                     @RequestParam String  transactionType, 
			                     @RequestParam double amount,@RequestParam String password,Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		User varifieduser=userservice.getUser(user.getEmail(), password);
		if(varifieduser==null) {
			model.addAttribute("error", "Invalid Password, please try again!");
		    return "transaction";
		}
		
		Account account=accountService.getAccountById(accountId);
		if(account==null) {
			model.addAttribute("error", "Invalid Account ID.");
            return "transaction";
		}
		
		Transaction transaction=new Transaction(0,account,transactionType,amount,
				new Timestamp(System.currentTimeMillis()));
		transactionService.saveTransaction(transaction);
		 accountService.updateBalance(accountId,amount,transactionType);
		model.addAttribute("message", "Transaction recorded successfully!");
		
		return "transaction";
		
		
	}
	
	@GetMapping("/viewAccountTransactions")
	public String viewTransaction(@RequestParam int accountId,Model model) {
		
		List<Transaction> transactions=transactionService.getTransactionsByAccountId(accountId);
		for(Transaction t:transactions) {
			System.out.println(t.getAmount());
		}
		model.addAttribute("transactions", transactions);
		return "viewTransactions";
		
	}
	
}
