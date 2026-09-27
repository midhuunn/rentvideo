package com.midhunn.rentvideo.dto;

import java.util.Date;

public class AuthResponse {

    private String token;
    private String tokenType;
    private Date expiresAt;

    public AuthResponse() {
    }

    public AuthResponse(String token, String tokenType, Date expiresAt) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}