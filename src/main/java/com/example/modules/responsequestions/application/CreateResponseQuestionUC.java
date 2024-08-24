package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

public class CreateResponseQuestionUC {
    private final ResponseQuestionService responseQuestionService;

    public CreateResponseQuestionUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void create(ResponseQuestion responseQuestion) {
        this.responseQuestionService.createResponseQuestion(responseQuestion);
    }
}