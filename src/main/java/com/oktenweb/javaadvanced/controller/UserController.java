package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.dto.AuthenticationResponse;
import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.service.IUserService;
import com.oktenweb.javaadvanced.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping(value = "/company/{company_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insertMovie(@RequestBody @Valid User user, @PathVariable int company_id){
        log.info("Handling POST /directors/{" + company_id + "} with object: " + user);
        return userService.insertUser(user, company_id);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse generateJWT(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(,authRequest.getUsername, authRequest.getPassword));
        return new AuthenticationResponse(jwtService.generateToken(authRequest.getUsername()));
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
