package com.faf.pad.thesis.domain.views;

import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class CompanyView extends ResourceSupport {

    private Long id;
    private String name;
    private Double rating;
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
