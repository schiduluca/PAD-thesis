package com.faf.pad.thesis.domain.converters;


import com.faf.pad.thesis.domain.Company;
import com.faf.pad.thesis.domain.views.CompanyView;
import com.google.common.base.Converter;

public class CompanyConverter extends Converter<Company, CompanyView> {
    @Override
    protected CompanyView doForward(Company company) {
        CompanyView companyView = new CompanyView();
        companyView.setCountry(company.getCountry());
        companyView.setName(company.getName());
        companyView.setRating(company.getRating());
        return companyView;
    }

    @Override
    protected Company doBackward(CompanyView companyView) {
        Company company = new Company();
        company.setCountry(companyView.getCountry());
        company.setName(companyView.getName());
        company.setRating(company.getRating());
        return company;
    }
}
