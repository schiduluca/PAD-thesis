package com.faf.pad.thesis.controllers;

import com.faf.pad.thesis.domain.views.CompanyListWrapper;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.services.CompanyService;
import com.faf.pad.thesis.services.HATEOASService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/companies")
public class CompanyController {

    @Autowired
    private final CompanyService companyService;

    @Autowired
    private final HATEOASService service;



    public CompanyController(CompanyService companyService, HATEOASService service) {
        this.companyService = companyService;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public HttpEntity<CompanyListWrapper> getAll(@QueryParam("fields") String fields) {
        List<CompanyView> collect = service.getLinksForList(companyService.getAll(fields));
        return new ResponseEntity<>(new CompanyListWrapper(collect), HttpStatus.OK);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public HttpEntity<CompanyView> getById(@PathVariable("id") Long id, @QueryParam("fields") String fields) {
        CompanyView linksForEntity = service.getLinksForEntity(companyService.findById(id, fields), id);
        return new ResponseEntity<>(linksForEntity, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompanyView createCompany(@Valid @RequestBody CompanyView companyView) {
        return companyService.create(companyView);
    }
}
