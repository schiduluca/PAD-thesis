package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.converters.CompanyConverter;
import com.faf.pad.thesis.domain.converters.CustomerConverter;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.faf.pad.thesis.repository.CompanyRepository;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.SyncService;
import org.springframework.beans.factory.annotation.Autowired;

public class SyncServiceImpl implements SyncService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CompanyRepository companyRepository;

    private final CompanyConverter companyConverter = new CompanyConverter();
    private final CustomerConverter customerConverter = new CustomerConverter();


    public SyncServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void syncCustomer(CustomerView customerView) {
        customerRepository.save(customerConverter.reverse().convert(customerView));
    }

    @Override
    public void syncCompany(CompanyView companyView) {
        companyRepository.save(companyConverter.reverse().convert(companyView));

    }
}
