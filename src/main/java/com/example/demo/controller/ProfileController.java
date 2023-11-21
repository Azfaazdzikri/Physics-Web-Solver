package com.example.demo.controller;

import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProfileService;
import com.example.demo.user.Profile;
import com.example.demo.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public ProfileController(UserRepository userRepository, ProfileRepository profileRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }


    @GetMapping({"/profile", "/profile.html"})
    public String profilePage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Boolean userLoggedIn = (Boolean) session.getAttribute("userLoggedIn");
        if (userLoggedIn != null && userLoggedIn) {
            String userEmail = (String) session.getAttribute("userEmail");

            if (userEmail != null) {
                User user = userRepository.findByEmail(userEmail);

                if (user != null) {
                    Profile profile = user.getProfile();

                    if (profile == null) {
                        profile = new Profile();
                        profile.setUser(user);
                    }

                    model.addAttribute("profile", profile);

                    // Check if the request path is /profile or /profile.html
                    String path = request.getServletPath();
                    if ("/profile".equals(path)) {
                        return "redirect:/profile.html";
                    } else {
                        // Handle other cases if needed
                        // e.g., return a different view or perform additional logic
                        return "profile";
                    }
                } else {
                    return "redirect:/profile-fail.html";
                }
            } else {
                return "redirect:/profile-fail.html";
            }
        } else {
            return "redirect:/profile-fail.html";
        }
    }


    @PostMapping("/update-profile")
    public String updateProfile(HttpServletRequest request,
                                @RequestParam String namaprofile, @RequestParam int nimprofile,
                                @RequestParam String tanggallahir, @RequestParam String jeniskelamin, @RequestParam String emailprofile,
                                @RequestParam String programstudi, @RequestParam String fakultasprofile,
                                @RequestParam String universitas) {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        User user = userRepository.findByEmail(userEmail);

        if (user != null) {
            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
                profile.setUser(user);
            }

            // Do not set the id, let Hibernate manage it
            profile.setNamaprofile(namaprofile);
            profile.setNimprofile(nimprofile);
            profile.setTanggallahir(tanggallahir);
            profile.setJeniskelamin(jeniskelamin);
            profile.setEmailprofile(emailprofile);
            profile.setProgramstudi(programstudi);
            profile.setFakultasprofile(fakultasprofile);
            profile.setUniversitas(universitas);

            profileRepository.save(profile);
            userRepository.save(user);

            return "redirect:/profile.html";
        } else {
            return "profile";
        }
    }




}