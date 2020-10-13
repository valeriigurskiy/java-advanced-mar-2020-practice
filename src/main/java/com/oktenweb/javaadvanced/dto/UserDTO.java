package com.oktenweb.javaadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {

    private int userId;
    private String name;
    private String password;
    private String companyName;
    private String companyAddress;

}
