package com.wine.model;

/**
 * Created by Judith on 21.05.2017.
 */
public class LoginUserResponse {
    public LoginUserResponse(String token, boolean isAdmin){
        this.token = token;
        this.isAdmin = isAdmin;
    }

    public String token;
    public boolean isAdmin;
}
