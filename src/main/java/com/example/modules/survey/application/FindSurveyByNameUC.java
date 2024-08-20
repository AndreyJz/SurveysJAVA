package com.example.modules.survey.application;

import com.example.modules.survey.domain.entity.Survey;
import com.example.modules.survey.domain.service.SurveySercive;

import java.util.Optional;

public class FindSurveyByNameUC {
    private final SurveySercive surveySercive;

    public FindSurveyByNameUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public Optional<Survey> find(String name) {
        return surveySercive.findSurveyByName(name);
    }
}
