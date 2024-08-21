package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;
import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;

import java.util.List;

public class ListSubresponseOptionsUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public ListSubresponseOptionsUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public List<SubresponseOptions> list() {
        return this.subresponseOptionsService.listSubresponseOptions();
    }
}