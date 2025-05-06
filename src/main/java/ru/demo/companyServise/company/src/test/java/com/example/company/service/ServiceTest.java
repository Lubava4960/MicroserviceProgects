package ru.demo.companyServise.company.src.test.java.com.example.company.service;

import org.springframework.stereotype.Service;
import ru.demo.companyServise.company.src.main.java.com.example.company.config.CompanyDetailsConfig;
import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;
import ru.demo.companyServise.company.src.main.java.com.example.company.repository.CompanyRepository;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyCreateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyUpdateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CompanyDetailsConfig companyDetailsConfig;
    @InjectMocks
    private ru.demo.companyServise.company.src.main.java.com.example.company.service.Service companyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompany() {
        CompanyCreateDto dto = new CompanyCreateDto();
        dto.setTitle("Test Title");
        dto.setBudget(140000.0);

        Company savedCompany = new Company();
        savedCompany.setId(1L);
        savedCompany.setTitle(dto.getTitle());
        savedCompany.setBudget(dto.getBudget());

        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);


        Company company = companyService.createCompany(dto);


        assertNotNull(company);
        assertEquals("Test Title", company.getTitle());
        assertEquals(140000.0, company.getBudget());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void getUsersByCompanyId() {
        long companyId = 1L;
        String url = "http://localhost:8083/users/company/" + companyId;

        when(companyDetailsConfig.getGetCompanyStatusUrl()).thenReturn("http://localhost:8083/users");

        List<User> expectedUsers = Arrays.asList(
                new User(1L, "Alice", "Ivanova", "555", "Email", 1L),
                new User(2L, "Bob", "Petrov", "666", "email2", 2L)
        );


        ResponseEntity<List<User>> responseEntity = ResponseEntity.ok(expectedUsers);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        List<User> actualUsers = companyService.getUsersByCompanyId(companyId);

        assertNotNull(actualUsers);
        assertEquals(2, actualUsers.size());
        assertEquals("Alice", actualUsers.get(0).getFirstName());
        assertEquals("Bob", actualUsers.get(1).getFirstName());
        verify(restTemplate, times(1)).exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class));
    }


    @Test
    void updateCompany() {
        Long companyId = 1L;
        Company existingCompany = new Company(companyId, "Old Title", 1000.0);
        CompanyUpdateDto companyUpdateDto = new CompanyUpdateDto("New Title", 1500.0);

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));


        when(companyRepository.save(any(Company.class))).thenAnswer(invocation -> {
            Company companyToSave = invocation.getArgument(0);
            return new Company(companyToSave.getId(), companyToSave.getTitle(), companyToSave.getBudget());

        });

        Company updatedCompany = companyService.updateCompany(companyId, companyUpdateDto);

        assertNotNull(updatedCompany);
        assertEquals("New Title", updatedCompany.getTitle());

        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, times(1)).save(existingCompany);
    }



}