package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.Optional;

public class FindSubresponseOptionsByIdUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public FindSubresponseOptionsByIdUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public Optional<SubresponseOptions> find(int id) {
        return this.subresponseOptionsService.findSubresponseOptionsById(id);
    }
}