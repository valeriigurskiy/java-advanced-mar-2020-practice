package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> getCompanies();

    Company save(Company company);

}
