package com.example.question.application;

import com.example.question.domain.service.QuestionService;

public class DeleteQuestionUC {
    private final QuestionService questionService;

    public DeleteQuestionUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void delete(int id) { this.questionService.deleteQuestion(id); }
}
