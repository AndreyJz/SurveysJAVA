package com.example.modules.question.application;

import com.example.modules.question.domain.entity.Question;
import com.example.modules.question.domain.service.QuestionService;

public class CreateQuestionUC {
    private final QuestionService questionService;

    public CreateQuestionUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void create(Question question) { this.questionService.createQuestion(question); }
}
