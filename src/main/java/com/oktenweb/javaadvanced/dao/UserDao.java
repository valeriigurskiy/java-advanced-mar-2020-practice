package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {

}
