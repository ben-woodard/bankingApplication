package com.coderscampus.assignment13.web;

import java.util.Arrays;
import java.util.Set;
import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	AccountService accountService;
	@Autowired
	AddressService addressService;
	
	@GetMapping("/register")
	public String getCreateUser (ModelMap model) {
		model.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")


	public String postCreateUser (User user) {
		userService.save(user);
		return "redirect:/register";
	}
	
	@GetMapping("/users")
	public String getAllUsers (ModelMap model) {
		Set<User> users = userService.findAll();
		model.put("users", users);
		if (users.size() == 1) {
			model.put("user", users.iterator().next());
		}
		return "users";
	}

	@PostMapping("/users")
	public String updateUserIfOneRegisteredUser(User user){
		userService.updateUserInformation(user);
		return "redirect:/users/"+user.getUserId();
	}
	
	@GetMapping("/users/{userId}")
	public String getOneUser (ModelMap model, @PathVariable Long userId) {
		User user = userService.findById(userId);
		model.put("accounts", user.getAccounts());
		model.put("users", Arrays.asList(user));
		model.put("user", user);
		return "users";
	}
	
	@PostMapping("/users/{userId}")
	public String postOneUser (User user) {
		userService.updateUserInformation(user);
		return "redirect:/users/"+user.getUserId();
	}
	
	@PostMapping("/users/{userId}/delete")
	public String deleteOneUser (@PathVariable Long userId) {
		userService.delete(userId);
		return "redirect:/users";
	}

	@GetMapping("/users/{userId}/account/{accountId}")
	public String getOneUserAccount (ModelMap model, @PathVariable Long userId, @PathVariable Long accountId){
		Account account = accountService.findByID(accountId);
		User user = userService.findById(userId);
		model.put("user", user);
		model.put("account", account);
		return "account";
	}

	@PostMapping("/users/{userId}/account/{accountId}")
	public String postNewAccountName(ModelMap model, @PathVariable Long accountId, @RequestParam String accountName, @PathVariable Long userId){
		Account account = accountService.saveAccountByIdAndName(accountId, accountName, userId);
		model.put("account", account);
		return "redirect:/users/{userId}/account/{accountId}";
	}

	@PostMapping("/users/{userId}/account")
	public String postCreateAccount(@PathVariable Long userId) {
		Account account = accountService.createAccountByUserId(userId);
		return "redirect:/users/{userId}/account/"+account.getAccountId();
	}
}
