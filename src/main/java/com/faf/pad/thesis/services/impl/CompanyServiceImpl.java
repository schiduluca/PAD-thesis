package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.Company;
import com.faf.pad.thesis.domain.Customer;
import com.faf.pad.thesis.domain.converters.CompanyConverter;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.faf.pad.thesis.repository.CompanyRepository;
import com.faf.pad.thesis.services.CompanyService;
import com.faf.pad.thesis.services.FieldSelectorService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final FieldSelectorService fieldSelectorService;

    @Auto
    private final RestTemplate restTemplate;

    @Autowired
    private final DiscoveryClient discoveryClient;

    private final CompanyConverter converter = new CompanyConverter();

    public CompanyServiceImpl(CompanyRepository companyRepository, FieldSelectorService fieldSelectorService, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.companyRepository = companyRepository;
        this.fieldSelectorService = fieldSelectorService;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }


    @Override
    public CompanyView findById(Long id, String fields) {
        return Optional.ofNullable(companyRepository.findOne(id))
                .map(converter::convert)
                .map(view -> fieldSelectorService.selectFields(view, fields))
                .orElseThrow(() -> new EntityNotFoundException("Company does not exist"));
    }

    @Override
    public List<CompanyView> getAll(String fields) {
        return companyRepository.findAll()
                .stream()
                .map(converter::convert)
                .map(view -> fieldSelectorService.selectFields(view, fields))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyView create(CompanyView view) {
        companyRepository.save(converter.reverse().convert(view));
        syncNodes(view);
        return view;
    }

    private void syncNodes(CompanyView view) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add((new MappingJackson2HttpMessageConverter()));
        HttpEntity<CompanyView> request = new HttpEntity<>(view, headers);

        discoveryClient.getServices().forEach(element -> {
            restTemplate.postForObject("http://" + element + "/api/sync/companies", request, CompanyView.class);
        });
    }
}
