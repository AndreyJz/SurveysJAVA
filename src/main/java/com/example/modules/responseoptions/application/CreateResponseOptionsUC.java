package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

public class CreateResponseOptionsUC {
    private final ResponseOptionsService responseOptionsService;

    public CreateResponseOptionsUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void create(ResponseOptions responseOptions) {
        this.responseOptionsService.createResponseOptions(responseOptions);
    }
}