package com.example.tpsoa.utils;

public class SessionInfo {
    public static String authToken;
    public static String refreshToken;

    public static void setTokens(String t, String rT){
        authToken = t;
        refreshToken = rT;
    }
}
