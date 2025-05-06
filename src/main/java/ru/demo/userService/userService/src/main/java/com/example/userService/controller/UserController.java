package ru.demo.userService.userService.src.main.java.com.example.userService.controller;

import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserCreateDto;

import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserUpdateDto;
import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import ru.demo.userService.userService.src.main.java.com.example.userService.service.Service;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final Service userService;
    @Operation(
            summary = "можно ввести данные нового  сотрудника",
            description = "введите данные",
            tags = "сотрудники"
    )

    @PostMapping
    public ResponseEntity<User> newUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User createdUser = userService.createUser(userCreateDto);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(
            summary = "получение сотрудника по id",
            description = "введите id сотрудника",
            tags = "сотрудники"
    )

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "обновить данные  сотрудника",
            description = "введите данные id сотрудника и измените данные ",
            tags = "сотрудники"
    )

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable long id, @RequestBody UserUpdateDto userUpdateDto) throws ChangeSetPersister.NotFoundException {

        return userService.updateUser((id), userUpdateDto);
    }


    @Operation(
            summary = "удалить данные  сотрудника",
            description = "введите id сотрудника",
            tags = "сотрудники"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "получить данные всех сотрудников",
            tags = "сотрудники"
    )

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @Operation(
            summary ="получить данные  компании по id",
            description = "введите id компании",
            tags = "компании"
    )

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<User>> getUsersByCompanyId(@PathVariable long companyId) {
        List<User> users = userService.getUsersByCompanyId(companyId);
        return ResponseEntity.ok(users);
    }


}

