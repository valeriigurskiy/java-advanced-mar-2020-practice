package com.oktenweb.javaadvanced.service;

import com.oktenweb.javaadvanced.dao.UserDao;
import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    private RestTemplate restTemplate;

    @Value("${userlogin}")
    private String username;
    @Value("${userpassword}")
    private String password;

    public UserDTO insertUser(User user, int companyId) {
        if (user.getName().charAt(0) < 65 || user.getName().charAt(0) > 90) {
            throw new CapitalLetterException("Name should start with capital letter");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " +
                Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

        HttpEntity<?> httpEntity = new HttpEntity<>(user, httpHeaders);
        final ResponseEntity<User> responseEntity = restTemplate
                .exchange("http://localhost:8080/users/1", HttpMethod.POST, httpEntity, User.class);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Unsuccessful request!");
        }
        return null;
    }

    public List<User> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " +
                Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        final ResponseEntity<User> responseEntity = restTemplate
                .exchange("http://localhost:8081/users?size=10", HttpMethod.GET, httpEntity, User.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.hasBody()) {
            return (List<User>) responseEntity.getBody();
        } else {
            throw new RuntimeException("Unsuccessful request!");
        }

    }

    public User getUser(int id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("No movie with id: " + id));
    }

    public User updateUser(User user) {
        return userDao.save(user);
    }

    public void removeUser(int id) {
        userDao.deleteById(id);
    }
}
