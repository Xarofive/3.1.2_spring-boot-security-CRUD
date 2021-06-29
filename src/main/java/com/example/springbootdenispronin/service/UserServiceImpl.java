package com.example.springbootdenispronin.service;

import com.example.springbootdenispronin.model.Role;
import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> index() {
        return usersRepository.findAll();
    }

    @Override
    public void save(User user) {

        if (!usersRepository.findUserByName(user.getUsername()).isPresent()) {
            user.setRoles(Collections.singleton(new Role("ROLE_USER")));
            usersRepository.save(user);
        }
    }

    @Override
    public void update(User user, int id) {
        usersRepository.save(user);
    }

    @Override
    public User showById(int id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User showByName(String name) {
        Optional<User> optionalUser = usersRepository.findUserByName(name);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = usersRepository.findUserByName(name).get();

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
