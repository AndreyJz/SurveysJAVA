package com.example.modules.question.application;

import com.example.modules.question.domain.entity.Question;
import com.example.modules.question.domain.service.QuestionService;

import java.util.List;

public class ListQuestionsUC {
    private final QuestionService questionService;

    public ListQuestionsUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> list() { return this.questionService.listQuestions(); }
}
