package com.example.login.application;

import com.example.login.domain.service.LoginService;

public class RoleUC {
    private final LoginService loginService;

    public RoleUC(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean checkRole(int id) { return this.loginService.role(id); }
}
