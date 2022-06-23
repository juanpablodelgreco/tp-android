package com.example.tpsoa.dtos.requests;

public class LoginRequest {
    private String env;
    private String email;
    private String password;

    public LoginRequest(String email, String password){
        this.env = "PROD";
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
