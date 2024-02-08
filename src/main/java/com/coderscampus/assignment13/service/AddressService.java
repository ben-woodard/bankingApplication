package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AddressRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private UserRepository userRepo;

    public Address save(User user) {
        Address address = user.getAddress();
        user.setAddress(address);
        address.setUser(user);
        address.setUserId(user.getUserId());
        userRepo.save(user);
        return addressRepo.save(user.getAddress());
    }
}
