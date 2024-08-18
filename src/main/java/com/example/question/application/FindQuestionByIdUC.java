package com.example.question.application;

import com.example.question.domain.entity.Question;
import com.example.question.domain.service.QuestionService;

import java.util.Optional;

public class FindQuestionByIdUC {
    private final QuestionService questionService;

    public FindQuestionByIdUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Optional<Question> find(int id) { return this.questionService.findQuestionById(id); }
}
