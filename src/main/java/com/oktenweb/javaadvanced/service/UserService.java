package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dao.CompanyDao;
import com.oktenweb.javaadvanced.dao.UserDao;
import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.Company;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;


    @Override
    public UserDTO insertUser(User user, int companyId) {
       if(user.getName().charAt(0) < 65 || user.getName().charAt(0) > 90){
           throw new CapitalLetterException("Name should start with capital letter");
       }
       final Company company = companyDao.getOne(companyId);
       user.setCompany(company);
       user = userDao.save(user);
       return new UserDTO(user.getId(), user.getName(), user.getPassword(), company.getCompanyName(), company.getAddress());
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(int id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id));
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void removeUser(int id) {
        userDao.deleteById(id);
    }
}
