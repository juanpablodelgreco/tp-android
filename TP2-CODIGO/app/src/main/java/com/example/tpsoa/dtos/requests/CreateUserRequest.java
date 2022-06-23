package com.example.tpsoa.dtos.requests;

import com.google.gson.annotations.SerializedName;

public class CreateUserRequest {
    private String env;
    private String name;
    @SerializedName("lastname")
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private int commission;
    private int group;

    public CreateUserRequest(
            String name,
            String lastName,
            String dni,
            String email,
            String password,
            int commission,
            int group
            ){
        this.env = "PROD";
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.commission = commission;
        this.group = group;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}

