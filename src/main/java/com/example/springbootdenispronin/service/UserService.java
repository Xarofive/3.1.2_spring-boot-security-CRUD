package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();

    void create(User user);

    void update(User user);

    User get(Long id);

    void delete(Long id);

    UserDetails loadUserByUsername(String name);

    User showByName(String name);
}
