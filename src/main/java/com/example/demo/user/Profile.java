package com.example.demo.user;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "profildb", schema = "public")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String namaprofile;
    private int nimprofile;
    private String tanggallahir;
    private String jeniskelamin;
    private String emailprofile;
    private String programstudi;
    private String fakultasprofile;
    private String universitas;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "penggunaid")
    private User user;
    public void setNamaprofile(String namaprofile){
        this.namaprofile = namaprofile;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNimprofile(int nimprofile){
        this.nimprofile = nimprofile;
    }
    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }


    public void setJeniskelamin(String jeniskelamin){
        this.jeniskelamin = jeniskelamin;
    }
    public void setEmailprofile(String emailprofile){
        this.emailprofile = emailprofile;
    }
    public void setProgramstudi(String programstudi){
        this.programstudi = programstudi;
    }
    public void setFakultasprofile(String fakultasprofile){
        this.fakultasprofile = fakultasprofile;
    }
    public void setUniversitas(String universitas){this.universitas = universitas;}
    public String getNamaprofile() {
        return namaprofile;
    }
    public int getNimprofile() {
        return nimprofile;
    }
    public String getTanggallahir() {
        return tanggallahir;
    }
    public String getJeniskelamin() {
        return jeniskelamin;
    }
    public String getEmailprofile() {
        return emailprofile;
    }
    public String getProgramstudi() {
        return programstudi;
    }
    public String getFakultasprofile() {return fakultasprofile;}
    public String getUniversitas() {
        return universitas;
    }

    public Long getId(){return id;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Transactional
    public void saveProfile(Profile profile) {
        // menyimpan atau memperbarui entitas
    }

}
