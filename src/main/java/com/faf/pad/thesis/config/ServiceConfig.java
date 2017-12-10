package com.faf.pad.thesis.config;


import com.faf.pad.thesis.repository.CompanyRepository;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.*;
import com.faf.pad.thesis.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(customerRepository, restTemplate);
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public SyncService syncService() {
        return new SyncServiceImpl(customerRepository);
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl(companyRepository, fieldSelectorService());
    }

    @Bean
    public HATEOASService hateoasService() {
        return new HATEOASServiceImpl();
    }

    @Bean
    public FieldSelectorService fieldSelectorService() {
        return new FieldSelectorServiceImpl();
    }

}


