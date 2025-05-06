package ru.demo.userService.userService.src.main.java.com.example.userService.dto;

import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto extends User {

    @NotEmpty
    private Long id;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "phoneNumber cannot be empty")
    private String phoneNumber;
    @Email(message = "Email should be valid")
    private String email;

}
