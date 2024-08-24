package com.example.modules.responsequestions.domain.service;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;

import java.util.List;
import java.util.Optional;

public interface ResponseQuestionService {
    void createResponseQuestion(ResponseQuestion responseQuestion);
    void updateResponseQuestion(ResponseQuestion responseQuestion);
    void deleteResponseQuestion(int id);
    Optional<ResponseQuestion> findResponseQuestionById(int id);
    Optional<ResponseQuestion> findResponseQuestionByName(String name);
    List<ResponseQuestion> listResponseQuestions();
    Optional<ResponseQuestion> findResponseQuestionsByText(String responseText);
}