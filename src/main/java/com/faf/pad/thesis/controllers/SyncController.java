package com.faf.pad.thesis.controllers;

import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.faf.pad.thesis.services.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sync")
public class SyncController {

    @Autowired
    private final SyncService syncService;

    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    void syncCustomer(@RequestBody CustomerView customerView) {
        syncService.syncCustomer(customerView);
    }

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    void syncCompany(@RequestBody CompanyView companyView) {
        syncService.syncCompany(companyView);
    }


}
