package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String index(Model model)
    {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getMovie(@ModelAttribute User user, int id){
        userService.insertUser(user, id);
        return "redirect:/";
    }

    @PostMapping(value = "/company/{company_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insertMovie(@RequestBody @Valid User user, @PathVariable int company_id){
        log.info("Handling POST /directors/{" + company_id + "} with object: " + user);
        return userService.insertUser(user, company_id);
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
