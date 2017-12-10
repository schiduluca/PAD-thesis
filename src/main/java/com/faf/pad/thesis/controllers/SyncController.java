package com.faf.pad.thesis.controllers;

import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.services.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sync/customers")
public class SyncController {

    @Autowired
    private final SyncService syncService;

    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @RequestMapping(method = RequestMethod.POST)
    void syncCustomer(@RequestBody Customer customer) {
        syncService.syncCustomer(customer);
    }
}
