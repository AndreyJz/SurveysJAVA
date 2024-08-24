package com.example.modules.survey.application;

import com.example.modules.survey.domain.service.SurveySercive;

public class DeleteSurveyUC {
    private final SurveySercive surveySercive;

    public DeleteSurveyUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public void delete(int id) { this.surveySercive.deleteSurvey(id); }
}
