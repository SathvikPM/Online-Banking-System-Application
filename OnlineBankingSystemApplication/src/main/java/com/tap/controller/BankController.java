package com.tap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.model.Account;
import com.tap.model.User;
import com.tap.service.AccountService;
import com.tap.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class BankController {
	
	   @Autowired
	   AccountService accountservice;
	   
	   @Autowired
	   UserService userservice;
	   
	   @GetMapping("/")
	   public String showHomePage(Model model) {
	   model.addAttribute("message","welcome to online Banking!");
	   return "home"; 
	  } 
	  
	  @GetMapping("/login")
	  public String showLoginPage() {
		  return "login";
	  }
	  
      @GetMapping("/register")
	  public  String showRegisterPage() {  
		  return "register";
	  }
      
      @GetMapping("/BankAccount")
  	  public String showAccountPage() {
  		return "account";
  	  }
  	
  	  @PostMapping("/account")
  	  public String createAccount(@ModelAttribute Account account,@RequestParam String password,  Model model,HttpSession session) {
  		User user=(User) session.getAttribute("user");
		User varifieduser=userservice.getUser(user.getEmail(), password);
		if(varifieduser==null) {
			model.addAttribute("error", "Invalid Password, please try again!");
		    return "account";
		}
  		accountservice.saveAccount(account);
  		model.addAttribute("account",account);
  		return "welcome";
  	  }
      
      
}
