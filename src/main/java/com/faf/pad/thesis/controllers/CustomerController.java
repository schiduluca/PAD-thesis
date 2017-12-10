package com.faf.pad.thesis.controllers;


import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.domain.views.CustomerListWrapper;
import com.faf.pad.thesis.services.CustomerService;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.faf.pad.thesis.services.HATEOASService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")

public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final HATEOASService hateoasService;


    public CustomerController(CustomerService customerService, HATEOASService hateoasService) {
        this.customerService = customerService;
        this.hateoasService = hateoasService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    HttpEntity<CustomerListWrapper> getAll(@QueryParam("fields") String fields) {
        List<CustomerView> linksForList = hateoasService.getLinksForList(customerService.getAll(fields));
        return new ResponseEntity<>(new CustomerListWrapper(linksForList), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    HttpEntity<CustomerView> getById(@PathVariable("id") Long id, @QueryParam("fields") String fields) {
        CustomerView linksForEntity = hateoasService.getLinksForEntity(customerService.findById(id, fields), id);
        return new ResponseEntity<>(linksForEntity, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    CustomerView createCustomer(@RequestBody CustomerView customer) {
        return customerService.create(customer);
    }
}
