package com.example.modules.responsequestions.application;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

import java.util.List;

public class ListResponseQuestionsUC {
    private final ResponseQuestionService responseQuestionService;

    public ListResponseQuestionsUC(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public List<ResponseQuestion> list() {
        return this.responseQuestionService.listResponseQuestions();
    }
}