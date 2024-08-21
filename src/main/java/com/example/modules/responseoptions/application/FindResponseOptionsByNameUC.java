package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.Optional;

public class FindResponseOptionsByNameUC {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseOptionsByNameUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public Optional<ResponseOptions> find(String name) { return this.responseOptionsService.findResponseOptionsByName(name); }
}
