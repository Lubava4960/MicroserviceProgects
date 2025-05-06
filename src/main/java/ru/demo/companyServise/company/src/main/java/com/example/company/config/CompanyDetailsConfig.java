package ru.demo.companyServise.company.src.main.java.com.example.company.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "userapi")
public class CompanyDetailsConfig {
    private String getCompanyStatusUrl;
}
