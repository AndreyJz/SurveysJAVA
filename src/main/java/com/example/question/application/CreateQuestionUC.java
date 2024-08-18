package com.example.question.application;

import com.example.question.domain.entity.Question;
import com.example.question.domain.service.QuestionService;

public class CreateQuestionUC {
    private final QuestionService questionService;

    public CreateQuestionUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void create(Question question) { this.questionService.createQuestion(question); }
}
