package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> index();

    void save(User user);

    void update(User user, int id);

    User showById(int id);

    User showByName(String name);

    void delete(int id);

    UserDetails loadUserByUsername(String name);


}
