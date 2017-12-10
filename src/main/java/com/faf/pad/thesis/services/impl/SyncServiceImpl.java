package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.SyncService;
import org.springframework.beans.factory.annotation.Autowired;

public class SyncServiceImpl implements SyncService {

    @Autowired
    private final CustomerRepository customerRepository;

    public SyncServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void syncCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
