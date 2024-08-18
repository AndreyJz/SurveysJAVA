package com.example.survey.domain.service;

import com.example.survey.domain.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveySercive {
    public List<Survey> listSurveys();
    public Optional<Survey> findSurveyByName(String name);
    public Optional<Survey> findSurveyById(int id);
}
