package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

public class CompanyService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userlogin}")
    private String username;
    @Value("${userpassword")
    private String password;

    public List<Company> getCompanies() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " +
                Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        final ResponseEntity<Company> responseEntity = restTemplate
                .exchange("http:/localhost:8080/companies?size=10", HttpMethod.GET, httpEntity, Company.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.hasBody()){
            return (List<Company>) responseEntity.getBody();
        }
        else {
            throw new RuntimeException("Unsuccessful request!");
        }

    }

    public Company save(Company company) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " +
                Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        HttpEntity<?> httpEntity = new HttpEntity<>(company, httpHeaders);

        final ResponseEntity<Company> responseEntity = restTemplate
                .exchange("http:/localhost:8080/companies/1", HttpMethod.POST, httpEntity, Company.class);

        if(responseEntity.getStatusCode() != HttpStatus.OK && !responseEntity.hasBody()){
            throw new RuntimeException("Unsuccessful request!");
        }
        return null;
    }
}
