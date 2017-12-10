package com.faf.pad.thesis.services;

import com.faf.pad.thesis.domain.views.CompanyView;

import java.util.List;

public interface CompanyService {
    CompanyView findById(Long id, String fields);

    List<CompanyView> getAll(String fields);

    CompanyView create(CompanyView view);
}
