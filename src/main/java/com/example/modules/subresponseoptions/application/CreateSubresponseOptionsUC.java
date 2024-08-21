package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;
import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;

public class CreateSubresponseOptionsUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public CreateSubresponseOptionsUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void create(SubresponseOptions subresponseOptions) {
        this.subresponseOptionsService.createSubresponseOptions(subresponseOptions);
    }
}