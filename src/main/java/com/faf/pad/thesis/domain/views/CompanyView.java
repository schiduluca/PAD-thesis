package com.faf.pad.thesis.domain.views;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyView  {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    private Double rating;

    @NotNull
    private String country;

    private List<CustomerView> customerViewList;

    public CompanyView() {
    }

    public CompanyView(String name, Double rating, String country, List<CustomerView> customerViewList) {
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.customerViewList = customerViewList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CustomerView> getCustomerViewList() {
        return customerViewList;
    }

    public void setCustomerViewList(List<CustomerView> customerViewList) {
        this.customerViewList = customerViewList;
    }
}
