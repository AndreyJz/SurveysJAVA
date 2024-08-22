package com.example.modules.subresponseoptions.application;

import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;

public class DeleteSubresponseOptionsUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public DeleteSubresponseOptionsUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void delete(int id) {
        this.subresponseOptionsService.deleteSubresponseOptions(id);
    }
}