package com.tap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.tap.model.User;
import com.tap.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/processUserRegister")
	public String processUserRegister(@ModelAttribute User user, HttpSession session,Model model) {
		model.addAttribute("user", user);
		session.setAttribute("user", user);
		if(userservice.emailExists(user.getEmail())) {
			model.addAttribute("error", "Email already in use. Please use a different email.");
			return "register";
		}
		userservice.saveUser(user);
		
		return "register-success";
		
	}
	
	@PostMapping("/userLogin")
	public String userLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		
		User user=	userservice.getUser(email,password);
		
		if(user==null) {
			model.addAttribute("error","Invalid email or password.");
			return "login";
		}
		session.setAttribute("password", password);
		session.setAttribute("user", user);
		
		return "welcome";
	    
	}
	
	
	
}
