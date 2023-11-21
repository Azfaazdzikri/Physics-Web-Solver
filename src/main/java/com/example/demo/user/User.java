package com.example.demo.user;

import jakarta.persistence.*;


@Entity
@Table(name = "penggunadb", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) { this.username = username;
    }

    public void setEmail(String email) { this.email = email;
    }

    public String getEmail(){return email;}
    public Profile getProfile() {
        return profile;
    }
    public Long getId() {return id;}
    public void setProfile(Profile profile) {
        this.profile = profile;
    }


}

