package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.converters.CustomerConverter;
import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.repository.CustomerRepository;
import com.faf.pad.thesis.services.CustomerService;
import com.faf.pad.thesis.domain.views.CustomerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final RestTemplate restTemplate;

    private final CustomerConverter converter = new CustomerConverter();


    public CustomerServiceImpl(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public CustomerView findById(Long id) {
        return converter.convert(customerRepository.findOne(id));
    }

    @Override
    public List<CustomerView> getAll() {
        return customerRepository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerView create(CustomerView customer) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add((new MappingJackson2HttpMessageConverter()));
        HttpEntity<CustomerView> request = new HttpEntity<>(customer, headers);

        restTemplate.postForObject("http://localhost:8080/api/sync/customers", request, Customer.class);
        return converter.convert(customerRepository.save(converter.reverse().convert(customer)));
    }
}
