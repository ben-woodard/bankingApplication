package com.coderscampus.assignment13.service;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressService addressService;
	@Autowired
	private AccountService accountService;

	public Set<User> findAll () {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}
	
	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}

	public User passwordCheck (User user) {
		if(user.getPassword() == ""){
			User dbUser = userRepo.findById(user.getUserId()).orElse(new User());
			user.setPassword(dbUser.getPassword());
		}
		return user;
	}

	public User updateUserInfo(User user) {
		passwordCheck(user);
		addressService.save(user);
		accountService.save(user);
		return userRepo.save(user);
	}

//	public User updateUserByUserId(Long userId) {
//		User existingUser = findById(userId);
//		addressService.save(existingUser);
//		accountService.save(existingUser);
//		return userRepo.save(existingUser);
//	}

	public User save(User user) {
		return userRepo.save(user);
	}

	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}
}
