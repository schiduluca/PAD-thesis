package com.faf.pad.thesis.domain.converters;


import com.faf.pad.thesis.domain.entities.Customer;
import com.faf.pad.thesis.domain.views.CustomerView;
import com.google.common.base.Converter;

public class CustomerConverter extends Converter<Customer, CustomerView> {

    @Override
    protected CustomerView doForward(Customer customer) {
        CustomerView customerView = new CustomerView();
        customerView.setName(customer.getName());
        customerView.setAge(customer.getAge());
        customerView.setSurname(customer.getSurname());
        customerView.setEmail(customer.getEmail());
        customerView.setPassword(customer.getPassword());
        return customerView;
    }

    @Override
    protected Customer doBackward(CustomerView customerView) {
        Customer customer = new Customer();
        customer.setAge(customerView.getAge());
        customer.setName(customerView.getName());
        customer.setSurname(customerView.getSurname());
        customer.setEmail(customerView.getEmail());
        customer.setPassword(customerView.getPassword());
        return customer;
    }


}
