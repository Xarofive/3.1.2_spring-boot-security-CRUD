package com.example.springbootdenispronin.controller;


import com.example.springbootdenispronin.model.User;
import com.example.springbootdenispronin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //+
    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        model.addAttribute("userToAdd", new User());
        return "admin";
    }

    //-
    @PostMapping("/admin")
    public String create(@ModelAttribute("userToAdd") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }

        userService.save(user);
        return "redirect:/admin";
    }

    //+
    @GetMapping("/user/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showById(id));
        return "user";
    }

    //+
    @GetMapping("/user")
    public String edit(Model model, Principal principal) {
        User user = userService.showByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    //+
    @PatchMapping("admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }

        userService.update(user, id);
        return "redirect:/admin";
    }

    //+
    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    //+
    @GetMapping("/hello")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    //+
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "login";
    }

}
