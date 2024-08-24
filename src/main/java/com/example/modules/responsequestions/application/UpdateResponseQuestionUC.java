package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

public class UpdateResponseQuestionUC {
    private final ResponseQuestionService responseQuestionService;

    public UpdateResponseQuestionUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void update(ResponseQuestion responseQuestion) {
        this.responseQuestionService.updateResponseQuestion(responseQuestion);
    }
}