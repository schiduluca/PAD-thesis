package com.faf.pad.thesis.domain.views;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class CompanyView extends ResourceSupport {

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
