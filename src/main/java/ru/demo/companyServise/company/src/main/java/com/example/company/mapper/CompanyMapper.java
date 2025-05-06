package ru.demo.companyServise.company.src.main.java.com.example.company.mapper;

import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyCreateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.dto.CompanyUpdateDto;
import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    // Метод для обновления компании из DTO
    static void updateUserFromDto(Company company, CompanyUpdateDto companyUpdateDto) {
        if (companyUpdateDto != null) {

            company.setTitle(companyUpdateDto.getTitle());
            company.setBudget(companyUpdateDto.getBudget());

        }
    }


    static Company toDTO(Company company) {
        if (company == null) {
            return null;
        }

        CompanyCreateDto companyDto = new CompanyCreateDto();
        companyDto.setId(company.getId());
        companyDto.setTitle(company.getTitle());
        company.setBudget(company.getBudget());
        return companyDto;
    }

}
