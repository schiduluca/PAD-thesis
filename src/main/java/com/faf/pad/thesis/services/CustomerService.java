package com.faf.pad.thesis.services;



import com.faf.pad.thesis.domain.views.CustomerView;

import java.util.List;

public interface CustomerService {

    CustomerView findById(Long id, String fields);

    List<CustomerView> getAll(String fields);

    CustomerView create(CustomerView customer);
}
