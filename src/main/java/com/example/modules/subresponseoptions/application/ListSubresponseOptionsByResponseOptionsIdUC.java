package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.List;

public class ListSubresponseOptionsByResponseOptionsIdUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public ListSubresponseOptionsByResponseOptionsIdUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public List<SubresponseOptions> list(int id) { return this.subresponseOptionsService.listSubresponseOptionsByResponseOptionsId(id); }
}