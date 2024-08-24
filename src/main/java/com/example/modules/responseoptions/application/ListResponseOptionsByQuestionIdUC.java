package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.List;

public class ListResponseOptionsByQuestionIdUC {
    private final ResponseOptionsService responseOptionsService;

    public ListResponseOptionsByQuestionIdUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public List<ResponseOptions> list(int questionId) {
        return this.responseOptionsService.listResponseOptionsByQuestionId(questionId);
    }
}