package com.faf.pad.thesis.repository;


import com.faf.pad.thesis.domain.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();
}
