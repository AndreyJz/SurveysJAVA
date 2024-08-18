package com.example.login.domain.service;

import com.example.login.domain.entity.Login;

import java.util.Optional;

public interface LoginService {
    public Optional<Login> loginCheck(String username, String password);
    public boolean role(int id);
}
