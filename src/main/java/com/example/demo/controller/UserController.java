package com.example.demo.controller;
import com.example.demo.repository.UserRepository;
import com.example.demo.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("userEmail", user.getEmail()); // Set userEmail sesuai dengan email pengguna yang berhasil login
            int oneDayInSeconds = 24 * 60 * 60;
            session.setMaxInactiveInterval(oneDayInSeconds);

            return "redirect:/login-success.html";
        } else {
            return "redirect:/login-fail.html";
        }
    }




    @GetMapping("/register")
    public String registerPage() {
        return "redirect:/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        if (userRepository.existsByEmail(email)) {
            return "redirect:/register-fail.html";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            return "redirect:/register-success.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index.html";
    }
}