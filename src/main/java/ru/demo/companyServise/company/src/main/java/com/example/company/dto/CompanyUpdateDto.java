package ru.demo.companyServise.company.src.main.java.com.example.company.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {

    private String title;

    private double budget;
}
