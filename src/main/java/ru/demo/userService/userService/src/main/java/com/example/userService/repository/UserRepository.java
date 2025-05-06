package ru.demo.userService.userService.src.main.java.com.example.userService.repository;

import ru.demo.userService.userService.src.main.java.com.example.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCompanyId(long companyId);
}
