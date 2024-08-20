package com.example.modules.question.application;

import com.example.modules.question.domain.entity.Question;
import com.example.modules.question.domain.service.QuestionService;

public class UpdateQuestionUC {
    private final QuestionService questionService;

    public UpdateQuestionUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void update(Question question) { this.questionService.updateQuestion(question); }
}
