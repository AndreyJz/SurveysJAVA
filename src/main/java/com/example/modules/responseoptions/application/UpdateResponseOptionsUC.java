package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

public class UpdateResponseOptionsUC {
    private final ResponseOptionsService responseOptionsService;

    public UpdateResponseOptionsUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void update(ResponseOptions responseOptions) {
        this.responseOptionsService.updateResponseOptions(responseOptions);
    }
}