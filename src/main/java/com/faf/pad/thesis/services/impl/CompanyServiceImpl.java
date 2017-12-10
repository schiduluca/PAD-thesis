package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.domain.converters.CompanyConverter;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.faf.pad.thesis.repository.CompanyRepository;
import com.faf.pad.thesis.services.CompanyService;
import com.faf.pad.thesis.services.FieldSelectorService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CompanyConverter converter = new CompanyConverter();

    public CompanyServiceImpl(CompanyRepository companyRepository, FieldSelectorService fieldSelectorService, RestTemplate restTemplate) {
        this.companyRepository = companyRepository;
        this.fieldSelectorService = fieldSelectorService;
        this.restTemplate = restTemplate;
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
        return view;
    }
}
