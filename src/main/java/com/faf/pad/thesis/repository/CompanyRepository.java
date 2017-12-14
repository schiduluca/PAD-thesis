package com.faf.pad.thesis.repository;

import com.faf.pad.thesis.domain.entities.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findAll();

}
