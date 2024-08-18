package com.example.question.application;

import com.example.question.domain.entity.Question;
import com.example.question.domain.service.QuestionService;

import java.util.Optional;

public class FindQuestionByNameUC {
    private final QuestionService questionService;

    public FindQuestionByNameUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Optional<Question> find(String name) { return this.questionService.findQuestionsByName(name); }
}
