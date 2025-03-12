package com.example.shop2.controllers;

import com.example.shop2.models.Role;
import com.example.shop2.models.User;
import com.example.shop2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {
        if(error.equals("username")) {
            model.addAttribute("error", "This login is already in use");
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        if(userRepository.findByUsername(username) != null) {
            return "redirect:/reg?error=username";
        }
        password = passwordEncoder.encode(password);
        User user = new User(username, password, email, true, Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
            User user = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("roles", Arrays.asList(Role.values()));
        return "user";
    }

    @PostMapping("/user/update")
    public String updateUser(Principal principal, User userForm) {
        User user = userRepository.findByUsername(principal.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoles(userForm.getRoles());
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/admin-panel")
    public String admin(Principal principal, Model model) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-panel";
    }

    @PostMapping("/admin-panel/user-{id}")
    public String showUserItems(@PathVariable(value = "id") long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return "redirect:/admin-panel"; // Если пользователь не найден, возвращаем в панель админа
        }

        User user = optionalUser.get();
        model.addAttribute("items", user.getItems());
        model.addAttribute("user", user);

        return "show-user";
    }
}
