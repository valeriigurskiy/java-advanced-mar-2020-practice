package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {
}
