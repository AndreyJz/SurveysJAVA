package com.example.survey.application;

import com.example.survey.domain.entity.Survey;
import com.example.survey.domain.service.SurveySercive;

import java.util.Optional;

public class FindSurveyByIdUC {
    private final SurveySercive surveySercive;

    public FindSurveyByIdUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public Optional<Survey> find(int id) {
        return this.surveySercive.findSurveyById(id);
    }
}
