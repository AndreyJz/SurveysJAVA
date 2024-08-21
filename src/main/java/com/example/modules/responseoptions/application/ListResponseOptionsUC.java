package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.List;

public class ListResponseOptionsUC {
    private final ResponseOptionsService responseOptionsService;

    public ListResponseOptionsUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public List<ResponseOptions> list() {
        return this.responseOptionsService.listResponseOptions();
    }
}