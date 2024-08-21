package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;
import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;

public class UpdateSubresponseOptionsUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public UpdateSubresponseOptionsUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void update(SubresponseOptions subresponseOptions) {
        this.subresponseOptionsService.updateSubresponseOptions(subresponseOptions);
    }
}