package com.faf.pad.thesis.services;



import com.faf.pad.thesis.domain.views.CustomerView;

import java.util.List;

public interface CustomerService {

    CustomerView findById(Long id);

    List<CustomerView> getAll();

    CustomerView create(CustomerView customer);
}
