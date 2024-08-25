package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.List;
import java.util.Optional;

public class FindSubresponseOptionsBySubresponseTextUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public FindSubresponseOptionsBySubresponseTextUC (SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public Optional<SubresponseOptions> find(String text) {
        return this.subresponseOptionsService.findSubresponseOptionsBySubresponseText(text);
    }
}