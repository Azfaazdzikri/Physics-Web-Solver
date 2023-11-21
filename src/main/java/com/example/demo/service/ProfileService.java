package com.example.demo.service;

import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.user.Profile;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

}



