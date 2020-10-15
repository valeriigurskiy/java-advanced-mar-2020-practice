package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.Company;
import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserController {

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUserIdWhenGettingUserReturnSuccessfulResponse() throws Exception {

        BDDMockito.given(userService.getUser(ArgumentMatchers.anyInt())).willReturn(new User(1, "Name", "Password", "email@email.com", null));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

}
