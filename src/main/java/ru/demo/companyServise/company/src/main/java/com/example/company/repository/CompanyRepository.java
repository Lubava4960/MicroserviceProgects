package ru.demo.companyServise.company.src.main.java.com.example.company.repository;

import ru.demo.companyServise.company.src.main.java.com.example.company.db.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {

}
