package com.faf.pad.thesis.services;

import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.domain.views.CustomerView;

public interface SyncService {
    void syncCustomer(CustomerView customer);

    void syncCompany(CompanyView companyView);
}
