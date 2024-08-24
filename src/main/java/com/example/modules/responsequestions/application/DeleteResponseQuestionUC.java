package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

public class DeleteResponseQuestionUC {
    private final ResponseQuestionService responseQuestionService;

    public DeleteResponseQuestionUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void delete(int id) {
        this.responseQuestionService.deleteResponseQuestion(id);
    }
}