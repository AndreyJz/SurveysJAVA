package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.List;

public class FindSubresponseOptionsByResponseOptionsIdUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public FindSubresponseOptionsByResponseOptionsIdUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public List<SubresponseOptions> find(int id) { return this.subresponseOptionsService.findSubresponseOptionsByResponseOptionsId(id); }
}