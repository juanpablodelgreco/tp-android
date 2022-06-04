package com.example.tpsoa.models;

public interface CreateAccountInteractor {
    void createAccount(OnFinishListener ofs, String firstName, String lastName, String dni, String email, int comission, String password, int group);
}
