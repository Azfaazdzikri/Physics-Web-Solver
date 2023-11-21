package com.example.demo.repository;
import com.example.demo.user.Profile;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findById(Long id);

    Optional<Profile> findByUser(User user);
}
