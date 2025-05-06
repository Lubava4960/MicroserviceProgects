package ru.demo.companyServise.company.src.main.java.com.example.company.dto;

import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;

import lombok.*;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class CompanyCreateDto extends Company {


    private Long id;

    private String title;

    private double budget;


}
