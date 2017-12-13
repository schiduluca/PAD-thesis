package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.converters.CustomerConverter;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.CustomerService;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.faf.pad.thesis.services.FieldSelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final FieldSelectorService fieldSelectorService;

    @Autowired
    private final DiscoveryClient discoveryClient;

    @Autowired
    private final Environment environment;

    private final CustomerConverter converter = new CustomerConverter();


    public CustomerServiceImpl(CustomerRepository customerRepository, RestTemplate restTemplate, FieldSelectorService fieldSelectorService, DiscoveryClient discoveryClient, Environment environment) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fieldSelectorService = fieldSelectorService;
        this.discoveryClient = discoveryClient;
        this.environment = environment;
    }


    @Override
    public CustomerView findById(Long id, String fields) {
        return Optional.ofNullable(customerRepository.findOne(id))
                .map(converter::convert)
                .map(view -> fieldSelectorService.selectFields(view, fields))
                .orElseThrow(() -> new EntityNotFoundException("Customer does not exist"));
    }

    @Override
    public List<CustomerView> getAll(String fields) {
        return customerRepository.findAll().stream()
                .map(converter::convert)
                .map(view -> fieldSelectorService.selectFields(view, fields))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerView create(CustomerView customer) {
        syncNodes(customer);
        return converter.convert(customerRepository.save(converter.reverse().convert(customer)));
    }

    private void syncNodes(CustomerView view) {
        String property = environment.getProperty("spring.application.name");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add((new MappingJackson2HttpMessageConverter()));
        HttpEntity<CustomerView> request = new HttpEntity<>(view, headers);

        discoveryClient.getServices().forEach(element -> {
            if(!element.equals(property)) {
                restTemplate.postForObject("http://" + element + "/api/sync/companies", request, CustomerView.class);
            }
        });
    }
}
