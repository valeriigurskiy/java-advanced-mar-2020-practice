package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dao.CompanyDao;
import com.oktenweb.javaadvanced.entity.Company;

import java.util.List;

public class CompanyService implements ICompanyService{

    private CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.findAll();
    }

    @Override
    public Company getCompanyById(int id) {
        return companyDao.getOne(id);
    }

    @Override
    public Company save(Company company) {
        return companyDao.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyDao.save(company);
    }

    @Override
    public void remove(int id) {
        companyDao.deleteById(id);
    }
}