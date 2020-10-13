package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> getCompanies();

    Company getCompanyById(int id);

    Company save(Company company);

    Company update(Company company);

    void remove(int id);

}
