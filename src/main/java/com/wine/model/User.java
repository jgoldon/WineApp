package com.wine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Judith on 21.05.2017.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String hash;

    @NotNull
    private boolean isAdmin;

    public User() {}
    public User(long id){
        this.id = id;
    }

    public User(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        this.username = username;
        this.hash = hashedPassword;
    }

    public boolean isMatch(String password){
        boolean matches = new BCryptPasswordEncoder().matches(password, hash);
        return matches;
    }

    public boolean getIsAdmin(){
        return this.isAdmin;
    }
}
