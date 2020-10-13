package com.oktenweb.javaadvanced.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 64)
    @NotBlank
    private String name;
    @Column(nullable = false, length = 64)
    @NotBlank
    private String password;
    @Column(nullable = false)
    @NotBlank
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    private Company company;


}
