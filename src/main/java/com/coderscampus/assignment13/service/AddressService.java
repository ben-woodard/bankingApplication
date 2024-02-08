package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepo;

    public Address save(User user) {
        if(user.getAddress() == null) {
            Address address = new Address();
            user.setAddress(address);
            address.setUser(user);
            address.setUserId(user.getUserId());
        } else {
            user.setAddress(user.getAddress());
            user.getAddress().setUser(user);
            user.getAddress().setUserId(user.getUserId());
        }
        return addressRepo.save(user.getAddress());
    }
}
