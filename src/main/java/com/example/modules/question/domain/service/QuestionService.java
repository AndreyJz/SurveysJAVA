package com.example.modules.question.domain.service;

import com.example.modules.question.domain.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    public void createQuestion(Question question);
    public void updateQuestion(Question question);
    public void deleteQuestion(int id);
    public Optional<Question> findQuestionById(int id);
    public List<Question> listQuestions();
    public Optional<Question> findQuestionsByName(String name);
    public List<Question> listQuestionsByChapterId(int id);
}
