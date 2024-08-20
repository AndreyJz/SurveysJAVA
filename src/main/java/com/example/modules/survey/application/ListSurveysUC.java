package com.example.modules.survey.application;

import com.example.modules.survey.domain.entity.Survey;
import com.example.modules.survey.domain.service.SurveySercive;

import java.util.List;

public class ListSurveysUC {
    private final SurveySercive surveySercive;

    public ListSurveysUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public List<Survey> list() {
        return this.surveySercive.listSurveys();
    }
}
