package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepo;
    private final UserService userService;

    @Autowired
    public AddressService(AddressRepository addressRepo, UserService userService) {
        this.addressRepo = addressRepo;
        this.userService = userService;
    }

    public void save(User user) {
        Address address = user.getAddress();
        user.setAddress(address);
        address.setUser(user);
        address.setUserId(user.getUserId());
        userService.save(user);
        addressRepo.save(user.getAddress());
    }
}
