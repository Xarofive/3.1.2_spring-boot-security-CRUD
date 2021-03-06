package com.example.springbootdenispronin.controller;

import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.RoleService;
import com.example.springbootdenispronin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userToAdd", new User());
        return "admin";
    }

    @GetMapping("/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserWithRolesById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user";
    }

    @PostMapping
    public String create(@ModelAttribute("userToAdd") @Valid User user, BindingResult bindingResult, @RequestParam Long[] roleIds) {

        if (bindingResult.hasErrors()) {
            log.error("Ошибка в запросе");
            return "redirect:/admin";
        }

        userService.create(user, roleIds);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PatchMapping
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam Long[] roleIds) {

        if (bindingResult.hasErrors()) {
            log.error("Ошибка в запросе");
            return "redirect:/admin";
        }

        userService.update(user, roleIds);
        return "redirect:/admin";
    }

}
