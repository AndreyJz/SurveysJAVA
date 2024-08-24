package com.example.modules.survey.application;

import com.example.modules.survey.domain.entity.Survey;
import com.example.modules.survey.domain.service.SurveySercive;

public class CreateSurveyUC {
    private final SurveySercive surveySercive;

    public CreateSurveyUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public void create(Survey survey) { this.surveySercive.createSurvey(survey); }
}
