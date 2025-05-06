package com.tap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tap.model.Account;
import com.tap.model.User;
import com.tap.service.AccountService;


import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/viewAccounts")
	public String viewAccounts(HttpSession session, Model model) {
		User user=(User)session.getAttribute("user");
		int userId=user.getUserId();
		List<Account> accounts=service.getAccountsById(userId);
		int count=accounts.size();	
		model.addAttribute("count", count);
		model.addAttribute("accounts",accounts);
		return "viewAccounts";
	
	}

}
