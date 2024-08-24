package com.example.modules.survey.application;

import com.example.modules.survey.domain.entity.Survey;
import com.example.modules.survey.domain.service.SurveySercive;

public class UpdateSurveyUC {
    private final SurveySercive surveySercive;

    public UpdateSurveyUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public void update(Survey survey) { this.surveySercive.updateSurvey(survey); }
}
