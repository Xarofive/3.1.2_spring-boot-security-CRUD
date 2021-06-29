package com.example.springbootdenispronin.repository;

import com.example.springbootdenispronin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

 Optional<User> findUserByName(String name);

}
