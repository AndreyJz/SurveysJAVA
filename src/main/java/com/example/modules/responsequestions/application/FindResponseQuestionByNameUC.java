package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

import java.util.Optional;

public class FindResponseQuestionByNameUC {
    private final ResponseQuestionService responseQuestionService;

    public FindResponseQuestionByNameUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public Optional<ResponseQuestion> find(String name) {
        return this.responseQuestionService.findResponseQuestionByName(name);
    }
}