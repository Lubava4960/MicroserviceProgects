package ru.demo.userService.userService.src.main.java.com.example.userService.service;

import jakarta.transaction.Transactional;
import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserCreateDto;

import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserUpdateDto;
import ru.demo.userService.userService.src.main.java.com.example.userService.mapper.UserMapper;
import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import ru.demo.userService.userService.src.main.java.com.example.userService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


@org.springframework.stereotype.Service

public class Service {
    private final   UserRepository userRepository;

@Autowired
    public Service(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User createUser(UserCreateDto userCreateDto) {
        if (userCreateDto.getFirstName() == null || userCreateDto.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        User user = new User();

        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setEmail(userCreateDto.getEmail());
        user.setCompanyId(userCreateDto.getCompanyId());

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserCreateDto updateUser(long id, UserUpdateDto userUpdateDto) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        UserMapper.updateUserFromDto(user, userUpdateDto);
        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

    public void deleteUserById(long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByCompanyId(long companyId) {
        return userRepository.findByCompanyId(companyId);
    }



}
