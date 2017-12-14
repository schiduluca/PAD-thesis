package com.faf.pad.thesis.config;


import com.faf.pad.thesis.repository.CompanyRepository;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.*;
import com.faf.pad.thesis.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(customerRepository, restTemplate, fieldSelectorService(), discoveryClient, environment);
    }

    @Bean
    @LoadBalanced
    public RestTemplate rest() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl(companyRepository, fieldSelectorService(), restTemplate, discoveryClient, environment);
    }



    @Bean
    public FieldSelectorService fieldSelectorService() {
        return new FieldSelectorServiceImpl();
    }


}


