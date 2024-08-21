package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.Optional;

public class FindResponseOptionsByIdUC {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseOptionsByIdUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public Optional<ResponseOptions> find(int id) {
        return this.responseOptionsService.findResponseOptionsById(id);
    }
}