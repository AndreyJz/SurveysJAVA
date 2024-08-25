package com.example.modules.responsequestions.domain.service;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;

import java.util.List;
import java.util.Optional;

public interface ResponseQuestionService {
    void createResponseQuestion(ResponseQuestion responseQuestion);
    Optional<ResponseQuestion> findResponseQuestionById(int id);
    Optional<ResponseQuestion> findResponseQuestionByName(String name);
    List<ResponseQuestion> listResponseQuestions();
}