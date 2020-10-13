package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.User;

import java.util.List;

public interface IUserService {

    UserDTO insertUser(User user, int companyId);

    List<User> getAllUsers();

    User getUser(int id);

    User updateUser(User user);

    void removeUser(int id);

}
