package ru.demo.userService.userService.src.main.java.com.example.userService.mapper;


import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserCreateDto;
import ru.demo.userService.userService.src.main.java.com.example.userService.dto.UserUpdateDto;
import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {

    // Метод для обновления пользователя из DTO

    static void updateUserFromDto(User user, UserUpdateDto userUpdateDto) {
        if (userUpdateDto != null) {
            // Используем значения из userUpdateDto для обновления user
            user.setFirstName(userUpdateDto.getFirstName());
            user.setLastName(userUpdateDto.getLastName());
            user.setEmail(userUpdateDto.getEmail());
            user.setPhoneNumber(userUpdateDto.getPhoneNumber());
            user.setCompanyId(user.getCompanyId());
        }
    }

    // Метод для преобразования объекта User в UserCreateDto
    static UserCreateDto toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserCreateDto userDto = new UserCreateDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCompanyId(user.getCompanyId());

        return userDto;
    }
}