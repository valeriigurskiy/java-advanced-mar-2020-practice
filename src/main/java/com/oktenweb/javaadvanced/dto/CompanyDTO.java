package com.oktenweb.javaadvanced.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyDTO {
    @JsonProperty("companies")
    private String companyName;
    private String companyAddress;

}
