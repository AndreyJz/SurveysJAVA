package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.List;

public class FindResponseOptionsByCategoryCatalogIdUC {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseOptionsByCategoryCatalogIdUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public List<ResponseOptions> find(int categoryCatalogId) {
        return this.responseOptionsService.findResponseOptionsByCategoryCatalogId(categoryCatalogId);
    }
}