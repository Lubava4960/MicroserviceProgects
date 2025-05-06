package ru.demo.companyServise.company.src.main.java.com.example.company.service;

import ru.demo.companyServise.company.src.main.java.com.example.company.config.CompanyDetailsConfig;
import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyCreateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyUpdateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.mapper.CompanyMapper;

import ru.demo.companyServise.company.src.main.java.com.example.company.repository.CompanyRepository;

import ru.demo.companyServise.company.src.main.java.com.example.company.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service

public class Service {

    private final CompanyRepository companyRepository;
    private final RestTemplate restTemplate;
    private final CompanyDetailsConfig companyDetailsConfig;

    @Autowired
    public Service(CompanyRepository companyRepository, RestTemplate restTemplate, CompanyDetailsConfig companyDetailsConfig) {
        this.companyRepository = companyRepository;
        this.restTemplate = restTemplate;
        this.companyDetailsConfig = companyDetailsConfig;
    }

    public Company createCompany(CompanyCreateDto companyCreateDto) {
        if (companyCreateDto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Название компании не может быть пустым");
        }

        if (companyCreateDto.getBudget() <= 0) {
            throw new IllegalArgumentException("Бюджет компании должен быть положительным числом");
        }

        Company company = new Company();
        company.setTitle(companyCreateDto.getTitle());
        company.setBudget(companyCreateDto.getBudget());
        return companyRepository.save(company);
    }


    public List<User> getUsersByCompanyId(long companyId) {
        String url = companyDetailsConfig.getGetCompanyStatusUrl() + "/company/" + companyId;
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Ошибка при вызове API: " + response.getStatusCode());
        }
    }

    public Company updateCompany(Long id, CompanyUpdateDto companyUpdateDto) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            CompanyMapper.updateUserFromDto(company, companyUpdateDto);
            System.out.println("Перед сохранением: Title = " + company.getTitle() + ", Budget = " + company.getBudget());
            Company updatedCompany = companyRepository.save(company);
            System.out.println("После сохранения: Title = " + updatedCompany.getTitle() + ", Budget = " + updatedCompany.getBudget());
            return CompanyMapper.toDTO(updatedCompany);

        } else {
            throw new EntityNotFoundException("Company not found");
        }
    }

    public Company findCompanyById(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Нет компании с таким ID: " + id));
    }

    public void deleteCompanyById(long id) {
        Company company = findCompanyById(id);
        companyRepository.delete(company);
    }

        public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

}





