package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.Company;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.exception.CapitalLetterException;
import com.oktenweb.javaadvanced.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getMovies(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getMovie(@PathVariable int id){
        return userService.getUser(id);
    }

    @PostMapping(value = "/company/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insertUser(@RequestBody @Valid User user, @PathVariable int companyId){
        return userService.insertUser(user,companyId);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateMovie(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        userService.removeUser(id);
    }

}
