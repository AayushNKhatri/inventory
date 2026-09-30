package com.invento.inventoy.auth.dto;

public class AuthDTO {
    public record LoginRequest(String username, String password){}
    public record RegisterRequest(String username, String password, String email){}
}
