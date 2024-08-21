package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

public class DeleteResponseOptionsUC {
    private final ResponseOptionsService responseOptionsService;

    public DeleteResponseOptionsUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void delete(int id) {
        this.responseOptionsService.deleteResponseOptions(id);
    }
}