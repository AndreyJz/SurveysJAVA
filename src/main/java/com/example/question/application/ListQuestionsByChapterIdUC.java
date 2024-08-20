package com.example.question.application;

import com.example.question.domain.entity.Question;
import com.example.question.domain.service.QuestionService;

import java.util.List;

public class ListQuestionsByChapterIdUC {
    private final QuestionService questionService;

    public ListQuestionsByChapterIdUC(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Question> list(int id) { return this.questionService.listQuestionsByChapterId(id); }
}
