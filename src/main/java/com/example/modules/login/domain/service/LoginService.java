package com.example.modules.login.domain.service;

import com.example.modules.login.domain.entity.Login;

import java.util.Optional;

public interface LoginService {
    public Optional<Login> loginCheck(String username, String password);
    public boolean role(int id);
}
