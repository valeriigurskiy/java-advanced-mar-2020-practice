package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.Company;
import com.oktenweb.javaadvanced.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company saveCompany(@RequestBody Company company){
        return companyService.save(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getCompanies();
    }




}
