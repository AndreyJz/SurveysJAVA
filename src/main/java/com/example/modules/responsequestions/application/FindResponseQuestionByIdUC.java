package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

import java.util.Optional;

public class FindResponseQuestionByIdUC {
    private final ResponseQuestionService responseQuestionService;

    public FindResponseQuestionByIdUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public Optional<ResponseQuestion> find(int id) {
        return this.responseQuestionService.findResponseQuestionById(id);
    }
}