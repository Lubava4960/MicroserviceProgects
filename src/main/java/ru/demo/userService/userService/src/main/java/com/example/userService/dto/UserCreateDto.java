package ru.demo.userService.userService.src.main.java.com.example.userService.dto;

import jakarta.validation.constraints.Size;
import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto  {




    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Long companyId;


}
