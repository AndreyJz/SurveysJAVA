package com.example.modules.login.application;

import com.example.modules.login.domain.entity.Login;
import com.example.modules.login.domain.service.LoginService;

import java.util.Optional;

public class LoginCheckUC {
    private final LoginService loginService;

    public LoginCheckUC(LoginService loginService) {
        this.loginService = loginService;
    }

    public Optional<Login> login(String username, String password) { return this.loginService.loginCheck(username, password); }
}
