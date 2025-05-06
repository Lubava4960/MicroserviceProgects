package ru.demo.companyServise.company.src.main.java.com.example.company.controller;

import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyCreateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyUpdateDto;

import ru.demo.companyServise.company.src.main.java.com.example.company.model.User;
import ru.demo.companyServise.company.src.main.java.com.example.company.service.Service;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final Service companyService;

    @Operation(
            summary = "можно ввести данные новой компании",
            description = "введите данные",
            tags = "компании"
    )
    @PostMapping
    public Company createCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        return companyService.createCompany(companyCreateDto);

    }

    @Operation(
            summary = "можно получить данные сотрудников по id компании",
            description = "введите данные",
            tags = "компании"
    )
    @GetMapping("/{companyId}")
    public ResponseEntity<List<User>> getUsersByCompanyId(@PathVariable long companyId) {
        List<User> users = companyService.getUsersByCompanyId(companyId);
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "обновить данные о компании",
            description = "введите  id  и измените данные ",
            tags = "компании"
    )

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable long id, @RequestBody CompanyUpdateDto companyUpdateDto) {

        return companyService.updateCompany((id), companyUpdateDto);
    }
    @Operation(
            summary = "удалить данные компании",
            description = "введите id компании",
            tags = "компании"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "получить список  компаний",
            tags = "компании"
    )
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

}