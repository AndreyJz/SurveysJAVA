package com.example.modules.survey.domain.service;

import com.example.modules.survey.domain.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveySercive {
    public void createSurvey(Survey survey);
    public void updateSurvey(Survey survey);
    public void deleteSurvey(int id);
    public List<Survey> listSurveys();
    public Optional<Survey> findSurveyByName(String name);
    public Optional<Survey> findSurveyById(int id);
}
